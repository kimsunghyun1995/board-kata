package com.kata.board.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.Comment

@Entity
class User constructor (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,

    @Comment("유저명")
    private val userName: String,

    @Comment("비밀번호")
    private val password: String,

    @Comment("닉네임")
    private val nickName: String,

    @Comment("이메일")
    private val email: String
): BaseEntity()