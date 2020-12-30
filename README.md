"# spring-boot-GraphQL" 

Method: POST | localhost:8080/rest/books

GraphQL Query:
{
  allBooks {
    isn
    title
    authors
  }
  book(id: "102") {
    title
    publisher
    authors
    publishedDate
  }
}

Response:
{
"data": {
"allBooks": [
  {
"isn": "101",
"title": "Book of Clouds",
"authors": [
  "Chloe Aridlis"
],
},
  {
"isn": "102",
"title": "Book of BigData",
"authors": [
  "Chloe Smith",
  "Ram Sharma"
],
}
],
"book": {
"title": "Book of BigData",
"publisher": "Kindle",
"authors": [
  "Chloe Smith",
  "Ram Sharma"
],
"publishedDate": "Oct 2018"
}
},
"errors": [],
"dataPresent": true,
"extensions": null
}
