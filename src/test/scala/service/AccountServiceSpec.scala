package service

import org.scalatest._

import config.ApplicationConfig
import model.{Account,User}
import repository.{AccountRepository,UserRepository}

class AccountServiceSpec extends FlatSpec with Matchers {
    class TestUserRepository extends UserRepository {
        def getUser(id: String): User = User("00002", "James", "Kirk")
    }

    class TestAccountRepository extends AccountRepository {
        def getAccount(user: User): Account = Account("Test Account", user)
    }

    "The AccountService" should "find an account when getAccount is called with an ApplicationConfig" in {
        val accountService = new RealAccountService()
        val userService = new RealUserService()
        val appConfig = ApplicationConfig(new TestAccountRepository(), new TestUserRepository())

        val accountComputation = for {
            user <- userService.getUser("00002")
            account <- accountService.getAccount(user)
        } yield account

        val account = accountComputation.run(appConfig)

        account.name shouldBe "Test Account"
    }
}