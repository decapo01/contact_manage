package com.contactmanager.domain

import java.util.*

data class Customer(
  val id      : UUID,
  val name    : String,
  val address : String,
  val city    : String,
  val state   : String,
  val zip     : String
)


data class Contact (

  val id         : UUID,
  val name       : String,
  val customerId : UUID?,
  val address    : String,
  val city       : String,
  val state      : String,
  val zip        : String
)

data class ContactViewItem(
  val contact      : Contact,
  val customerName : String
)