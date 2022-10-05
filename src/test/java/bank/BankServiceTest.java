package bank;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class BankServiceTest {
    private final BankService bankService = new BankService();

    @Test
    public void addUserTest() {
        boolean expectedResultOne = bankService.addUser(new User("Vladimir", "232323"));
        boolean expectedResultTwo = bankService.addUser(new User("Vladimir", "232323"));

        Assertions.assertTrue(expectedResultOne);
        Assertions.assertFalse(expectedResultTwo);
    }

    @Test
    public void addAccountTest() {
        boolean resultAddUser = bankService.addUser(new User("Vladimir", "232323"));

        boolean resultAddAccountUserOne = bankService.addAccount("232323", new Account("123", 150));
        boolean resultAddAccountUserTwo = bankService.addAccount("232323", new Account("333", 250));
        boolean resultAddAccountUserTwoDuplicate = bankService.addAccount("232323", new Account("333", 550));

        Assertions.assertTrue(resultAddUser);

        Assertions.assertTrue(resultAddAccountUserOne);
        Assertions.assertTrue(resultAddAccountUserTwo);
        Assertions.assertFalse(resultAddAccountUserTwoDuplicate);

        Assertions.assertEquals(250, bankService.findByRequisite("232323", "333").get().getBalance());
    }

    @Test
    public void addBalanceToAccountTest() {
        boolean resultAddUser = bankService.addUser(new User("Vladimir", "232323"));

        boolean resultAddAccountUserOne = bankService.addAccount("232323", new Account("123", 150));

        boolean addBalanceSuccessFirst = bankService.addBalanceToAccount("232323", "123", 100);
        boolean addBalanceSuccessSecond = bankService.addBalanceToAccount("232323", "123", 50);

        boolean addBalanceOnIncorrectAccount = bankService.addBalanceToAccount("232323", "555", 23);

        Assertions.assertTrue(resultAddUser);

        Assertions.assertTrue(resultAddAccountUserOne);

        Assertions.assertTrue(addBalanceSuccessFirst);
        Assertions.assertTrue(addBalanceSuccessSecond);

        Assertions.assertFalse(addBalanceOnIncorrectAccount);

        Assertions.assertEquals(300, bankService.findByRequisite("232323", "123").get().getBalance());
    }

    @Test
    public void withDrawBalanceFromAccount() {
        boolean resultAddUser = bankService.addUser(new User("Vladimir", "232323"));

        boolean resultAddAccountUserOne = bankService.addAccount("232323", new Account("123", 150));

        boolean addBalanceSuccessFirst = bankService.addBalanceToAccount("232323", "123", 100);
        boolean addBalanceSuccessSecond = bankService.addBalanceToAccount("232323", "123", 50);

        boolean success = bankService.withDrawBalanceFromAccount("232323", "123", 10);
        boolean notSuccess = bankService.withDrawBalanceFromAccount("232323", "123", 1000);

        Assertions.assertTrue(resultAddUser);

        Assertions.assertTrue(resultAddAccountUserOne);

        Assertions.assertTrue(addBalanceSuccessFirst);
        Assertions.assertTrue(addBalanceSuccessSecond);

        Assertions.assertTrue(success);
        Assertions.assertFalse(notSuccess);

        Assertions.assertEquals(290, bankService.findByRequisite("232323", "123").get().getBalance());
    }

    @Test
    public void transferMoneyTest() {
        boolean resultAddUserOne = bankService.addUser(new User("Vladimir", "232323"));
        boolean resultAddAccountUserOne = bankService.addAccount("232323", new Account("111", 150));

        boolean resultAddUserTwo = bankService.addUser(new User("Oleg", "121212"));
        boolean resultAddAccountUserTwo = bankService.addAccount("121212", new Account("333", 500));

        boolean transferOne = bankService.transferMoney("232323", "111", "121212", "333", 200);
        boolean transferTwo = bankService.transferMoney("232323", "111", "121212", "555", 10);
        boolean transferThree = bankService.transferMoney("232323", "111", "121212", "333", 40);

        Assertions.assertTrue(resultAddUserOne);
        Assertions.assertTrue(resultAddAccountUserOne);

        Assertions.assertTrue(resultAddUserTwo);
        Assertions.assertTrue(resultAddAccountUserTwo);

        Assertions.assertFalse(transferOne);
        Assertions.assertFalse(transferTwo);
        Assertions.assertTrue(transferThree);

        Assertions.assertEquals(110, bankService.findByRequisite("232323", "111").get().getBalance());
        Assertions.assertEquals(540, bankService.findByRequisite("121212", "333").get().getBalance());
    }

    @Test
    public void removeAccountAndTransferMoney() {
        boolean addUserSuccess = bankService.addUser(new User("Vladimir", "232323"));

        boolean resultAddAccountUserOne = bankService.addAccount("232323", new Account("111", 150));
        boolean resultAddAccountUserTwo = bankService.addAccount("232323", new Account("222", 555));
        boolean resultAddAccountUserThree = bankService.addAccount("232323", new Account("333", 150));
        boolean resultAddAccountUserFour = bankService.addAccount("232323", new Account("444", 150));

        Assertions.assertTrue(addUserSuccess);

        Assertions.assertTrue(resultAddAccountUserOne);
        Assertions.assertTrue(resultAddAccountUserTwo);
        Assertions.assertTrue(resultAddAccountUserThree);
        Assertions.assertTrue(resultAddAccountUserFour);

        boolean removeResult = bankService.deleteAccountByRequisite("232323", "222");

        Assertions.assertTrue(removeResult);

        List<Account> accountList = List.of(
                bankService.findByRequisite("232323", "111").get(),
                bankService.findByRequisite("232323", "333").get(),
                bankService.findByRequisite("232323", "444").get()
        );

        boolean deletedResult = bankService.findByRequisite("232323", "222").isPresent();

        Assertions.assertFalse(deletedResult);

        List<Account> result = accountList.stream().filter(account -> account.getBalance() == 705).toList();

        Assertions.assertEquals(1, result.size());
    }
}