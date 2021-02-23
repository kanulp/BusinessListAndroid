package com.kanulp.buisnesslistapp.model

data class BusinessDataModel (
    val id: String,
    val alias: String,
    val name: String,
    val imageURL: String,
    val isClaimed: Boolean,
    val isClosed: Boolean,
    val url: String,
    val phone: String,
    val displayPhone: String,
    val reviewCount: Long,
    val categories: List<Category1>,
    val rating: Long,
    val location: Location,
    val coordinates: Coordinates,
    val photos: List<String>,
    val price: String,
    val hours: List<Hour>,
    val transactions: List<String>
)

data class Category1 (
    val alias: String,
    val title: String
)

data class Coordinates (
    val latitude: Double,
    val longitude: Double
)

data class Hour (
    val open: List<Open>,
    val hoursType: String,
    val isOpenNow: Boolean
)

data class Open (
    val isOvernight: Boolean,
    val start: String,
    val end: String,
    val day: Long
)

data class Location (
    val address1: String,
    val address2: String,
    val address3: String,
    val city: String,
    val zipCode: String,
    val country: String,
    val state: String,
    val displayAddress: List<String>,
    val crossStreets: String
)
