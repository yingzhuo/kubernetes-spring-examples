package kse.backend.service

import java.util.UUID

import org.springframework.stereotype.Service

sealed trait UUIDService {

  def uuid(n: Int = 1): Array[String]

}

@Service
protected class UUIDServiceImpl extends AnyRef with UUIDService {

  override def uuid(n: Int): Array[String] = {
    val ss = for (_ <- 1 to n) yield UUID.randomUUID().toString
    ss.toArray
  }

}
