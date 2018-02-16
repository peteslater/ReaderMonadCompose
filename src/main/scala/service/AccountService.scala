package scala

import cats.data.Reader

import config.ApplicationConfig
import model.{Account,User}
import repository.AccountRepository

trait AccountService {
    def getAccount(user: User): Reader[ApplicationConfig,Account]
}

class RealAccountService extends AccountService {
    def getAccount(user: User): Reader[ApplicationConfig,Account] = Reader[ApplicationConfig,Account] { applicationConfig =>
        applicationConfig.accountRepository.getAccount(user)
    }
}