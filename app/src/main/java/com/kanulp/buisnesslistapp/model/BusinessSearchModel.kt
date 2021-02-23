package com.kanulp.buisnesslistapp.model

data class BusinessSearchModel (
    val categories: List<Category>,
    val businesses: List<Business>,
    val terms: List<Term>
)

data class Business (
    val id: String,
    val name: String
)

data class Category (
    val alias: String,
    val title: String
)

data class Term (
    val text: String
)
