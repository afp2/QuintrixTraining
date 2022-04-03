package com.example.npsapiapp.api

import java.lang.IllegalArgumentException

data class ParksResponse(
    val total: String,
    val limit: String,
    val start: String,
    val data: List<ParkObject>
)

data class ParkObject(
    val id: String,
    val url: String,
    val fullName: String,
    val parkCode: String,
    val description: String,
    val latitude: String,
    val longitude: String,
    val latLong: String,
    val activities: List<Activity>,
    val topics: List<Activity>,
    val states: String,
    val contacts: Contacts,
    val entranceFees: List<Entrance>,
    val entrancePasses: List<Entrance>,
    val fees: List<Any?>,
    val directionsInfo: String,
    val directionsUrl : String,
    val operatingHours: List<OperatingHour>,
    val addresses: List<Address>,
    val images: List<Image>,
    val weatherInfo: String,
    val name: String,
    val designation: String
) {
    override fun toString(): String {
        return url + System.getProperty("line.separator") +
                fullName + " " + parkCode + System.getProperty("line.separator") +
                description + System.getProperty("line.separator") + "Activities=" + activities.toString() + System.getProperty("line.separator") +
                "States = " + states + System.getProperty("line.separator") + "Contact=" + contacts.toString() + System.getProperty("line.separator") +
                directionsInfo + System.getProperty("line.separator") + directionsUrl + System.getProperty("line.separator") +
                operatingHours.toString() + System.getProperty("line.separator") + addresses.toString() + System.getProperty("line.separator") +
                weatherInfo + System.getProperty("line.separator") + designation + System.getProperty("line.separator")

    }
}

data class Activity(
    val id : String,
    val name : String
) {
    override fun toString(): String {
        return name
    }
}

data class Address(
    val postalCode: String,
    val city: String,
    val stateCode: String,
    val line1: String,
    val type: AddressType,
    val line3: String,
    val line2: String
) {
    override fun toString(): String {
        return type.value + System.getProperty("line.separator") + line1 + System.getProperty("line.separator") +
                line2 + System.getProperty("line.separator") +
                line3 + System.getProperty("line.separator") + city + " " + stateCode + " " + postalCode +
                System.getProperty("line.separator")
    }
}

enum class AddressType(val value : String){
    Mailing("Mailing"),
    Physical("Physical");
    companion object {
        fun fromValue(value : String) : AddressType = when (value) {
            "Mailing" -> Mailing
            "Physical" -> Physical
            else -> throw IllegalArgumentException()
        }
    }
}

data class Contacts (
    val phoneNumbers: List<PhoneNumber>,
    val emailAddresses: List<EmailAddress>
) {
    override fun toString(): String {
        return phoneNumbers.map { it.toString() }.toString() + System.getProperty("line.separator") +
                emailAddresses.map { it.toString()  }.toString()
    }
}

data class EmailAddress (
    val description: String,
    val emailAddress: String
) {
    override fun toString(): String {
        return "$description $emailAddress"
    }
}

data class PhoneNumber (
    val phoneNumber: String,
    val description: String,
    val extension: String,
    val type: PhoneNumberType
) {
    override fun toString(): String {
        return type.value + " " + phoneNumber + " " + extension + " " + description
    }
}

enum class PhoneNumberType(val value: String) {
    Fax("Fax"),
    TTY("TTY"),
    Voice("Voice");

    companion object {
        fun fromValue(value: String): PhoneNumberType = when (value) {
            "Fax"   -> Fax
            "TTY"   -> TTY
            "Voice" -> Voice
            else    -> throw IllegalArgumentException()
        }
    }
}

data class Entrance (
    val cost: String,
    val description: String,
    val title: String
)

data class Image (
    val credit: String,
    val title: String,
    val altText: String,
    val caption: String,
    val url: String
)

data class OperatingHour (
    val exceptions: List<Exception>,
    val description: String,
    val standardHours: Hours,
    val name: String
) {
    override fun toString(): String {
        return "$name $description $standardHours $exceptions"
    }
}

data class Exception (
    val exceptionHours: Hours,
    val startDate: String,
    val name: String,
    val endDate: String
) {
    override fun toString(): String {
        return "$name $startDate $endDate" + System.getProperty("line.separator") + "$exceptionHours"
    }
}

data class Hours (
    val wednesday: String? = null,
    val monday: String? = null,
    val thursday: String? = null,
    val sunday: String? = null,
    val tuesday: String? = null,
    val friday: String? = null,
    val saturday: String? = null
) {
    override fun toString(): String {
        return "Sunday=$sunday  Monday=$monday  Tuesday=$tuesday  Wednesday=$wednesday  Thursday=$thursday  Friday=$friday  Saturday=$saturday"
    }
}

