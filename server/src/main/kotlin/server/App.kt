package server

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients.create
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Indexes
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.bson.Document


@Suppress("UNUSED_PARAMETER")
fun main() {
    val connString = ConnectionString(
            "mongodb+srv://ronit:39WiOlp2rzFfsc5D@blazifychat.bpmqz.mongodb.net/Users?w=majority"
    )
    val settings = MongoClientSettings.builder()
            .applyConnectionString(connString)
            .retryWrites(true)
            .build()
    val mongoClient = create(settings)

    embeddedServer(Netty, 8080) {
        routing { 
            get("/") {
                call.respondText("All Routes: /signup, /login, /delete, /changepassword")
            }
            get("/signup") {
                call.respondText("Please use a post function for this to work")
            }
            post("/signup") {
                var args = (call.receive<String>()).split(" | ")
                var collection: MongoCollection<Document> = mongoClient
                        .getDatabase("Users")
                        .getCollection("user-password");

                var document: Document = Document("name", args[0])
                        .append("password", (args[1]).hashCode())
                        .append("email", args[2])
                        .append("phone-number", args[3])
                collection.insertOne(document)
                call.respondText("Saved ${args[0]} to Database")
            }
            get("/login") {
                call.respondText("Please use a post function for this to work")

            }
            post("/login") {
                var args = (call.receive<String>()).split(" | ")
                var collection: MongoCollection<Document> = mongoClient
                        .getDatabase("Users")
                        .getCollection("user-password");

                collection.find(Document("name", args[0])
                        .append("password", args[1].hashCode())
                        .append("email", args[2])
                        .append("ph-no", args[3])
                ).forEach {
                    println(it)
                }
            }
            get("/delete") {
                call.respondText("Please use a delete function for this to work")
            }
            get("/changepassword") {
                call.respondText("Please use a post function for this to work")

            }
         }
    }.start(wait = true)
    print("Ready!")

}






