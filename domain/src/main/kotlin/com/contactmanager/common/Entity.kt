package com.contactmanager.common

import java.sql.ResultSet


interface Id<T> {

  val item: T
}

interface Entity<ID : Id<*>> {

  val id : ID
}


interface Criteria<ID : Id<*>>{

  val idEq: ID?
    get() = null


  val idIn: List<ID>?
    get() = null

  val limit: Int?

  val page: Int?

  val sort: String?

}


interface simpleCriteria<T>{

  val idEq    : T?
  val idNotEq : T?
  val idIn    : T?
  val idNotIn : T?
  val limit   : Int?
  val page    : Int?
  val sort    : String?
}


interface SortItem

interface CriteriaField


interface Repository<ENTITY : Entity<ID>,ID : Id<*>,CRITERIA : Criteria<ID>>{

  val table: String

  fun toParams(entity: ENTITY): Map<String,Any>

  fun fromResultSet(rs: ResultSet,rowNo: Int): ENTITY

  fun selectAllSql() = "select * from ${table} "

  fun insertSql(entity: ENTITY) = {

    val cols = toParams(entity).keys.joinToString(", ")

    val questionMarks = toParams(entity).keys.map{"?"}.joinToString(", ")

    "insert into ${table} ( ${cols} ) values ( ${questionMarks} )"
  }

  fun updateSql(entity: ENTITY): String {

    val cols =
      toParams(entity)
      .keys
      .filter { it != "id" }
      .map { "${it} = ? " }
      .joinToString { ", " }

    return "update ${table} set ${cols} where id = ? "
  }

  fun deleteSql(id: ID) =
    "delete from ${table} where id = ? "


  fun insert(entity: ENTITY){

    println("")
  }

}