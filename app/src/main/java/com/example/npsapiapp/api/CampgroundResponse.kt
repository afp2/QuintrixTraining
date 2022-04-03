package com.example.npsapiapp.api

data class CampgroundResponse(
    val total : String,
    val limit : String,
    val start : String,
    val data : List<CampgroundObject>
)

data class CampgroundObject(
    val id: String,
    val url: String,
    val name: String,
    val parkCode: String,
    val description: String,
    val latitude: String,
    val longitude: String,
    val latLong: String,
    val audioDescription: String,
    val isPassportStampLocation: String,
    val passportStampLocationDescription: String,
    val passportStampImages: List<Any?>,
    val geometryPoiId: String,
    val reservationInfo: String,
    val reservationUrl: String,
    val regulationsurl: String,
    val regulationsOverview: String,
    val amenities: Amenities,
    val contacts: CampgroundContacts,
    val fees: List<Fee>,
    val directionsOverview: String,
    val directionsUrl: String,
    val operatingHours: List<CampgroundOperatingHour>,
    val addresses: List<CampgroundAddress>,
    val images: List<CampgroundImage>,
    val weatherOverview: String,
    val numberOfSitesReservable: String,
    val numberOfSitesFirstComeFirstServe: String,
    val campsites: Campsites,
    val accessibility: Accessibility,
    val lastIndexedDate: String
) {
    override fun toString(): String {
        return url + System.getProperty("line.separator") + name + System.getProperty("line.separator") +
                description + System.getProperty("line.separator") +
                reservationInfo + " " + reservationUrl + System.getProperty("line.separator") +
                regulationsOverview + " " + regulationsurl + System.getProperty("line.separator") +
                amenities.toString() + System.getProperty("line.separator") + contacts.toString() + System.getProperty("line.separator") +
                fees.toString() + System.getProperty("line.separator") + directionsUrl + System.getProperty("line.separator") +
                directionsOverview + System.getProperty("line.separator") + operatingHours.toString() + System.getProperty("line.separator") +
                addresses.map { it.toString() }.toString() + System.getProperty("line.separator") + weatherOverview + System.getProperty("line.separator") +
                numberOfSitesReservable + System.getProperty("line.separator") + numberOfSitesFirstComeFirstServe + System.getProperty("line.separator") +
                campsites.toString() + System.getProperty("line.separator") + accessibility.toString() + System.getProperty("line.separator")

    }
}

data class Accessibility (
    val wheelchairAccess: String,
    val internetInfo: String,
    val cellPhoneInfo: String,
    val fireStovePolicy: String,
    val rvAllowed: String,
    val rvInfo: String,
    val rvMaxLength: String,
    val additionalInfo: String,
    val trailerMaxLength: String,
    val adaInfo: String,
    val trailerAllowed: String,
    val accessRoads: List<AccessRoad>,
    val classifications: List<Classification>
) {
    override fun toString(): String {
        return "Wheelchair Access = $wheelchairAccess" + System.getProperty("line.separator") +
                "Internet Info = $internetInfo" + System.getProperty("line.separator") +
                "Cell Phone Info = $cellPhoneInfo" + System.getProperty("line.separator") +
                "Fire Stove Policy = $fireStovePolicy" + System.getProperty("line.separator") +
                "RV Allowed = $rvAllowed" + System.getProperty("line.separator") +
                "RV Info = $rvInfo" + System.getProperty("line.separator") +
                "RV Max Length = $rvMaxLength" + System.getProperty("line.separator") +
                "Additional Info = $additionalInfo" + System.getProperty("line.separator") +
                "Trailer Max Length = $trailerMaxLength" + System.getProperty("line.separator") +
                "More Info = $adaInfo" + System.getProperty("line.separator") +
                "Trailers Allowed = $trailerAllowed" + System.getProperty("line.separator") +
                "Access Roads = " + accessRoads.toString() + System.getProperty("line.separator") +
                "Classifications = " + classifications.toString()
    }
}

enum class AccessRoad(val value: String) {
    NoRoads("No Roads"),
    PavedRoadsAllVehiclesOK("Paved Roads - All vehicles OK"),
    UnpavedRoadsAllVehiclesOKInGoodWeather("Unpaved Roads - All vehicles OK in good weather");

    companion object {
        public fun fromValue(value: String): AccessRoad = when (value) {
            "No Roads"                                        -> NoRoads
            "Paved Roads - All vehicles OK"                   -> PavedRoadsAllVehiclesOK
            "Unpaved Roads - All vehicles OK in good weather" -> UnpavedRoadsAllVehiclesOKInGoodWeather
            else                                              -> throw IllegalArgumentException()
        }
    }
}

enum class Classification(val value: String) {
    DesignatedPrimitiveCampsites("Designated Primitive Campsites"),
    DevelopedCampground("Developed Campground"),
    LimitedDevelopmentCampground("Limited Development Campground"),
    PrimitiveCampingAreas("Primitive Camping Areas");

    companion object {
        public fun fromValue(value: String): Classification = when (value) {
            "Designated Primitive Campsites" -> DesignatedPrimitiveCampsites
            "Developed Campground"           -> DevelopedCampground
            "Limited Development Campground" -> LimitedDevelopmentCampground
            "Primitive Camping Areas"        -> PrimitiveCampingAreas
            else                             -> throw IllegalArgumentException()
        }
    }
}

data class CampgroundAddress (
    val postalCode: String,
    val city: String,
    val stateCode: String,
    val line1: String,
    val type: CampgroundAddressType,
    val line3: Line3,
    val line2: String
) {
    override fun toString(): String {
        return type.value + System.getProperty("line.separator") +
                line1 + System.getProperty("line.separator") +
                line2 + System.getProperty("line.separator") +
                line3 + System.getProperty("line.separator") +
                city + " " + stateCode + " " + postalCode + System.getProperty("line.separator")
    }
}

enum class Line3(val value: String) {
    Empty(""),
    FortMason("Fort Mason"),
    MarinHeadlands("Marin Headlands");

    companion object {
        public fun fromValue(value: String): Line3 = when (value) {
            ""                -> Empty
            "Fort Mason"      -> FortMason
            "Marin Headlands" -> MarinHeadlands
            else              -> throw IllegalArgumentException()
        }
    }
}

enum class CampgroundAddressType(val value: String) {
    Mailing("Mailing"),
    Physical("Physical");

    companion object {
        public fun fromValue(value: String): CampgroundAddressType = when (value) {
            "Mailing"  -> Mailing
            "Physical" -> Physical
            else       -> throw IllegalArgumentException()
        }
    }
}

data class Amenities (
    val trashRecyclingCollection: Amphitheater,
    val toilets: List<String>,
    val internetConnectivity: Amphitheater,
    val showers: List<Shower>,
    val cellPhoneReception: Amphitheater,
    val laundry: Amphitheater,
    val amphitheater: Amphitheater,
    val dumpStation: Amphitheater,
    val campStore: Amphitheater,
    val staffOrVolunteerHostOnsite: Amphitheater,
    val potableWater: List<PotableWater>,
    val iceAvailableForSale: Amphitheater,
    val firewoodForSale: Amphitheater,
    val foodStorageLockers: Amphitheater
) {
    override fun toString(): String {
        return "Trash Recycling Collection = $trashRecyclingCollection" + System.getProperty("line.separator") +
                "Toilets = $toilets" + System.getProperty("line.separator") +
                "Internet Connectivity = $internetConnectivity" + System.getProperty("line.separator") +
                "Showers = $showers" + System.getProperty("line.separator") +
                "Cell Phone Reception = $cellPhoneReception" + System.getProperty("line.separator") +
                "Laundry = $laundry" + System.getProperty("line.separator") +
                "Amphitheater = $amphitheater" + System.getProperty("line.separator") +
                "Dumb Station = $amphitheater" + System.getProperty("line.separator") +
                "Camp Store = $campStore" + System.getProperty("line.separator") +
                "Staff or Volunteer Host Onsite = $staffOrVolunteerHostOnsite" + System.getProperty("line.separator") +
                "Potable Water = $potableWater" + System.getProperty("line.separator") +
                "Ice for Sale = $iceAvailableForSale" + System.getProperty("line.separator") +
                "Firewood for Sale = $firewoodForSale" + System.getProperty("line.separator") +
                "Food Storage Lockers = $foodStorageLockers" + System.getProperty("line.separator")
    }

}

enum class Amphitheater(val value: String) {
    Empty(""),
    No("No"),
    YesSeasonal("Yes - seasonal"),
    YesYearRound("Yes - year round");

    companion object {
        public fun fromValue(value: String): Amphitheater = when (value) {
            ""                 -> Empty
            "No"               -> No
            "Yes - seasonal"   -> YesSeasonal
            "Yes - year round" -> YesYearRound
            else               -> throw IllegalArgumentException()
        }
    }

    override fun toString(): String {
        return value
    }
}

enum class PotableWater(val value: String) {
    ButNotPotable(" but not potable"),
    NoWater("No water"),
    Water("Water"),
    YesSeasonal("Yes - seasonal"),
    YesYearRound("Yes - year round");

    companion object {
        public fun fromValue(value: String): PotableWater = when (value) {
            " but not potable" -> ButNotPotable
            "No water"         -> NoWater
            "Water"            -> Water
            "Yes - seasonal"   -> YesSeasonal
            "Yes - year round" -> YesYearRound
            else               -> throw IllegalArgumentException()
        }
    }

    override fun toString(): String {
        return value
    }
}

enum class Shower(val value: String) {
    ColdSeasonal("Cold- Seasonal"),
    FreeSeasonal("Free - Seasonal"),
    HotSeasonal("Hot - Seasonal"),
    HotYearRound("Hot - Year Round"),
    None("None");

    companion object {
        public fun fromValue(value: String): Shower = when (value) {
            "Cold- Seasonal"   -> ColdSeasonal
            "Free - Seasonal"  -> FreeSeasonal
            "Hot - Seasonal"   -> HotSeasonal
            "Hot - Year Round" -> HotYearRound
            "None"             -> None
            else               -> throw IllegalArgumentException()
        }
    }

    override fun toString(): String {
        return value
    }
}

data class Campsites (
    val totalSites: String,
    val group: String,
    val horse: String,
    val tentOnly: String,
    val electricalHookups: String,
    val rvOnly: String,
    val walkBoatTo: String,
    val other: String
) {
    override fun toString(): String {
        return "Campsites" + System.getProperty("line.separator") +
                "Total sites = $totalSites" + System.getProperty("line.separator") +
                "Group = ${if(group == "0") "false" else "true"}" + System.getProperty("line.separator") +
                "Horse = ${if(horse == "0") "false" else "true"}" + System.getProperty("line.separator") +
                "Tent Only = ${if(tentOnly == "0") "false" else "true"}" + System.getProperty("line.separator") +
                "Electrical Hookups = ${if (electricalHookups == "0") "false" else "true"}" + System.getProperty("line.separator") +
                "RV Only = ${if (rvOnly == "0") "false" else "true"}" + System.getProperty("line.separator") +
                "Walk Boat to = ${if (walkBoatTo == "0") "false" else "true"}" + System.getProperty("line.separator") +
                "Other = $other" + System.getProperty("line.separator")
    }
}

data class CampgroundContacts (
    val phoneNumbers: List<CampgroundPhoneNumber>,
    val emailAddresses: List<CampgroundEmailAddress>
) {
    override fun toString(): String {
        return phoneNumbers.map { it.toString() }.toString() + System.getProperty("line.separator") +
                emailAddresses.map { it.toString() }.toString()
    }
}

data class CampgroundEmailAddress (
    val description: String,
    val emailAddress: String
) {
    override fun toString(): String {
        return "$description $emailAddress"
    }
}

data class CampgroundPhoneNumber (
    val phoneNumber: String,
    val description: String,
    val extension: String,
    val type: CampgroundPhoneNumberType
) {
    override fun toString(): String {
        return type.value + " " + phoneNumber + " " + extension + " " + description
    }
}

enum class CampgroundPhoneNumberType(val value: String) {
    Fax("Fax"),
    TTY("TTY"),
    Voice("Voice");

    companion object {
        public fun fromValue(value: String): CampgroundPhoneNumberType = when (value) {
            "Fax"   -> Fax
            "TTY"   -> TTY
            "Voice" -> Voice
            else    -> throw IllegalArgumentException()
        }
    }
}

data class Fee (
    val cost: String,
    val description: String,
    val title: String
) {
    override fun toString(): String {
        return "$title, $description, $cost"
    }
}

data class CampgroundImage (
    val credit: String,
    val crops: List<Any?>,
    val title: String,
    val altText: String,
    val caption: String,
    val url: String
)

data class CampgroundOperatingHour (
    val exceptions: List<CampgroundException>,
    val description: String,
    val standardHours: CampgroundHours,
    val name: String
) {
    override fun toString(): String {
        return "$name $description $standardHours $exceptions"
    }
}

data class CampgroundException (
    val exceptionHours: Hours,
    val startDate: String,
    val name: String,
    val endDate: String
) {
    override fun toString(): String {
        return "$name $startDate $endDate" + System.getProperty("line.separator") + "$exceptionHours"
    }
}

data class CampgroundHours (
    val wednesday: Day? = null,
    val monday: Day? = null,
    val thursday: Day? = null,
    val sunday: Day? = null,
    val tuesday: Day? = null,
    val friday: Day? = null,
    val saturday: Day? = null
) {
    override fun toString(): String {
        return "Sunday=$sunday  Monday=$monday  Tuesday=$tuesday  Wednesday=$wednesday  Thursday=$thursday  Friday=$friday  Saturday=$saturday"
    }
}

enum class Day(val value: String) {
    AllDay("All Day"),
    Closed("Closed");

    companion object {
        public fun fromValue(value: String): Day = when (value) {
            "All Day" -> AllDay
            "Closed"  -> Closed
            else      -> throw IllegalArgumentException()
        }
    }
}



