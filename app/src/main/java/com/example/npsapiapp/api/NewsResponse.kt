package com.example.npsapiapp.api

data class NewsResponse(
    val total: String,
    val limit: String,
    val start: String,
    val data: List<NewsObject>
)
data class NewsObject(
    val id: String,
    val url: String,
    val title: String,
    val parkCode: String,
    val abstract: String,
    val image: NewsImage,
    val relatedParks: List<RelatedPark>,
    val relatedOrganizations: List<RelatedOrganization>,
    val latitude: Any? = null,
    val longitude: Any? = null,
    val geometryPoiId: String,
    val releaseDate: String,
    val lastIndexedDate: String
) {
    override fun toString() : String {
        return url + System.getProperty("line.separator") +
                title + System.getProperty("line.separator") +
                abstract + System.getProperty("line.separator") +
                releaseDate + System.getProperty("line.separator")
    }
}

data class NewsImage (
    val url: String,
    val credit: String,
    val altText: String,
    val title: String,
    val description: String,
    val caption: String
)

data class RelatedOrganization (
    val id: String,
    val url: String,
    val name: String
)

data class RelatedPark (
    val states: String,
    val parkCode: String,
    val designation: String,
    val fullName: String,
    val url: String,
    val name: String
)
