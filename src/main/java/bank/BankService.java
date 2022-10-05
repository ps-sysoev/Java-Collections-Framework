package bank;

import java.util.*;

/**
 * 1. Реализовать логику банковского сервиса.
 * 2. Написать unit тесты. Покрыть нужно все методы данного класса.
 * 3. Проверки на null и пустоту строк делать не нужно.
 *  Подразумевается что сервис из вне защищен от таких случаев.
 */
public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет пользователя, изначательно у пользователя 0 счетов
     *
     * @param user добавляемый пользователь
     * @return true - пользователь добавлен успешно
     */
    public boolean addUser(User user) {
        return users.putIfAbsent(user, new ArrayList<>()) == null;
    }

    /**
     * Метод добавляет счет пользователю
     *
     * @param passport паспортные данные пользователя
     * @param accountAdded добавляемый счет
     * @return true - счет добавлен успешно
     */
    public boolean addAccount(String passport, Account accountAdded) {
        Optional<Account> account = findByRequisite(passport, accountAdded.getRequisite());

        if (account.isEmpty()) {
            Optional<User> user = findByPassport(passport);

            if (user.isPresent()) {
                List<Account> accountList = users.get(user.get());
                accountList.add(accountAdded);

                return true;
            }
        }

        return false;
    }

    /**
     * Метод добавляет деньги на счет пользователя
     *
     * @param passport паспортные данные пользователя
     * @param requisite реквизиты счета
     * @param balance добавляемая сумма на счет
     * @return true - деньги успешно добавлены
     */
    public boolean addBalanceToAccount(String passport, String requisite, double balance) {
        Optional<Account> account = findByRequisite(passport, requisite);

        if (account.isPresent()) {
            account.get().increaseBalance(balance);

            return true;
        }

        return false;
    }

    /**
     * Метод должен снять деньги со счета пользователя
     *
     * @param passport паспортные данные пользователя
     * @param requisite реквизиты счета
     * @param balance снимаемая сумма со счета
     * @return true - деньги успешно сняты
     */
    public boolean withDrawBalanceFromAccount(String passport, String requisite, double balance) {
        Optional<Account> account = findByRequisite(passport, requisite);

        if (account.isPresent() && account.get().getBalance() >= balance) {
            account.get().reduceBalance(balance);

            return true;
        }

        return false;
    }

    /**
     * Перевести деньги со счета на счет
     *
     * @param passportFrom паспортные данные пользователя отправителя
     * @param requisiteFrom реквизиты счета отправителя
     * @param passportTo паспортные данные пользователя получателя
     * @param requisiteTo реквизиты счета получателя
     * @param balance сумма перевода
     * @return true - сумма переведена успешно
     */
    public boolean transferMoney(String passportFrom, String requisiteFrom,
                                 String passportTo, String requisiteTo, double balance) {
        Optional<Account> accountFrom = findByRequisite(passportFrom, requisiteFrom);
        Optional<Account> accountTo = findByRequisite(passportTo, requisiteTo);

        if (accountFrom.isEmpty() || accountTo.isEmpty()) {
            return false;
        }

        if (accountFrom.get().getBalance() >= balance) {
            accountFrom.get().reduceBalance(balance);
            accountTo.get().increaseBalance(balance);

            return true;
        }

        return false;
    }

    /**
     * Метод удаляет счет пользователя, если на счету лежат деньги, то их нужно перевести на рандомный
     * любой другой счет пользователя, если же такого счета не существует, то отменить операцию.
     *
     * @param passport паспортные данные пользователя
     * @param requisite реквизиты счета
     * @return true - счет успешно удален
     */
    public boolean deleteAccountByRequisite(String passport, String requisite) {
        Optional<Account> account = findByRequisite(passport, requisite);

        if (account.isPresent()) {
            User user = findByPassport(passport).get();
            List<Account> accountList = users.get(user);

            accountList.remove(account.get());

            int randomAccountNumber = randomAccount(accountList.size());
            accountList.get(randomAccountNumber).increaseBalance(account.get().getBalance());

            return true;
        }

        return false;
    }

    private int randomAccount(int accountSize) {
        return (int) (accountSize * Math.random());
    }

    /**
     * Метод ищет счет по его реквизитам
     *
     * @param passport паспортные данные пользователя
     * @param requisite реквизиты счета
     * @return найденный счет пользователя
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);

        return user.flatMap(value -> users.get(value)
                .stream()
                .filter(account -> account.getRequisite().equals(requisite))
                .findFirst());
    }

    /**
     * Ищет пользователя по его паспортным данным
     *
     * @param passport паспортные данные пользователя
     * @return найденный пользователь
     */
    public Optional<User> findByPassport(String passport) {
        return users.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }
}