package com.example.userdetails.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    @Column(unique = true, nullable = false)
    val username: String,
    
    @Column(unique = true, nullable = false)
    val email: String,
    
    @Column(name = "first_name", nullable = false)
    val firstName: String,
    
    @Column(name = "last_name", nullable = false)
    val lastName: String,
    
    @Column(name = "date_of_birth")
    val dateOfBirth: LocalDate? = null,
    
    @Column(name = "phone_number")
    val phoneNumber: String? = null,
    
    @Column
    val address: String? = null
)
