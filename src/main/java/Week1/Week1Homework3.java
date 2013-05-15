package Week1;

import java.net.UnknownHostException;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class Week1Homework3 {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
        DB database = client.getDB("m101");
        DBCollection collection = database.getCollection("funnynumbers");

        // Not necessary yet to understand this.  It's just to prove that you
        // are able to run a command on a mongod server
        AggregationOutput output =
                collection.aggregate(
                        new BasicDBObject("$group",
                                new BasicDBObject("_id", "$value")
                                        .append("count", new BasicDBObject("$sum", 1)))
                        ,
                        new BasicDBObject("$match", new BasicDBObject("count",
                                new BasicDBObject("$gt", 2))),
                        new BasicDBObject("$sort", new BasicDBObject("_id", 1))
                );

        int answer = 0;
        for (DBObject doc : output.results()) {
            answer += (Double) doc.get("_id");
        }

        System.out.println("THE ANSWER IS: " + answer);
    }
}