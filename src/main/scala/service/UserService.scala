package service

import cats.data.Reader

import config.ApplicationConfig
import model.User
import repository.UserRepository

trait UserService {
  def getUser(id: String): Reader[ApplicationConfig,User]
}

class RealUserService extends UserService {
  def getUser(id: String): Reader[ApplicationConfig,User] = Reader[ApplicationConfig,User] { applicationConfig =>
    applicationConfig.userRepository.getUser(id)
  }
}
