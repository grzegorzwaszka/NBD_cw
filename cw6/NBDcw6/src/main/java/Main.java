import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.kv.UpdateValue;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;

import java.net.UnknownHostException;

public class Main {
    public static class Comic{
        public String title;
        public String author;
        public String illustrator;
        public Integer numberOfCopies;
    }

    public static class UpdateComic extends UpdateValue.Update<Comic> {
        private final Comic update;
        public UpdateComic(Comic update){
            this.update = update;
        }

        @Override
        public Comic apply(Comic t) {
            if(t == null) {
                t = new Comic();
            }

            t.author = update.author;
            t.title = update.title;
            t.illustrator = update.illustrator;
            t.numberOfCopies = update.numberOfCopies;


            return t;
        }
    }

    // This will create a client object that we can use to interact with Riak
    private static RiakCluster setUpCluster() throws UnknownHostException {
        // This example will use only one node listening on localhost:10017
        RiakNode node = new RiakNode.Builder()
                .withRemoteAddress("127.0.0.1")
                .withRemotePort(8087)
                .build();

        // This cluster object takes our one node as an argument
        RiakCluster cluster = new RiakCluster.Builder(node)
                .build();

        // The cluster must be started to work, otherwise you will see errors
        cluster.start();

        return cluster;
    }

    public static void main( String[] args ) {
        try {
            // First, we'll create a basic object storing a movie quote
           /* RiakObject quoteObject = new RiakObject()
                    .setContentType("application/json")
                    .setValue(BinaryValue.create("You're dangerous, Maverick"));
            System.out.println("Basic object created");

            // In the new Java client, instead of buckets you interact with Namespace
            // objects, which consist of a bucket AND a bucket type; if you don't
            // supply a bucket type, "default" is used; the Namespace below will set
            // only a bucket, without supplying a bucket type
            Namespace quotesBucket = new Namespace("quotes");

            // With our Namespace object in hand, we can create a Location object,
            // which allows us to pass in a key as well
            Location quoteObjectLocation = new Location(quotesBucket, "Iceman");
            System.out.println("Location object created for quote object");

            // With our RiakObject in hand, we can create a StoreValue operation
            StoreValue storeOp = new StoreValue.Builder(quoteObject)
                    .withLocation(quoteObjectLocation)
                    .build();
            System.out.println("StoreValue operation created");

            // And now we can use our setUpCluster() function to create a cluster
            // object which we can then use to create a client object and then
            // execute our storage operation
            RiakCluster cluster = setUpCluster();
            RiakClient client = new RiakClient(cluster);
            System.out.println("Client object successfully created");

            StoreValue.Response storeOpResp = client.execute(storeOp);
            System.out.println("Object storage operation successfully completed");

            // Now we can verify that the object has been stored properly by
            // creating and executing a FetchValue operation
            FetchValue fetchOp = new FetchValue.Builder(quoteObjectLocation)
                    .build();
            RiakObject fetchedObject = client.execute(fetchOp).getValue(RiakObject.class);
            assert(fetchedObject.getValue().equals(quoteObject.getValue()));
            System.out.println("Success! The object we created and the object we fetched have the same value");

            // Now update the fetched object
            fetchedObject.setValue(BinaryValue.create("You can be my wingman any time."));
            StoreValue updateOp = new StoreValue.Builder(fetchedObject)
                    .withLocation(quoteObjectLocation)
                    .build();
            StoreValue.Response updateOpResp = client.execute(updateOp);
            updateOpResp = client.execute(updateOp);

            // And we'll delete the object
            DeleteValue deleteOp = new DeleteValue.Builder(quoteObjectLocation)
                    .build();
            client.execute(deleteOp);
            System.out.println("Quote object successfully deleted");
*/

            RiakCluster cluster = setUpCluster();
            RiakClient client = new RiakClient(cluster);

            Comic spiderMan = new Comic();
            spiderMan.title = "Spider Man Original";
            spiderMan.author = "Stan Lee";
            spiderMan.illustrator = "Andrew Andrew";
            spiderMan.numberOfCopies= 1;

            Namespace comicsBucket = new Namespace("s16372Comics");
            Location spidermanLocation = new Location(comicsBucket, "spider_man");
            StoreValue storeComicOp = new StoreValue.Builder(spiderMan)
                    .withLocation(spidermanLocation)
                    .build();
            client.execute(storeComicOp);
            System.out.println("Spider Man Comicbook added to Riak");

            System.out.println("Fetching added comicbook...");

            FetchValue fetchSpiderManOp = new FetchValue.Builder(spidermanLocation)
                    .build();
            Comic comic = client.execute(fetchSpiderManOp).getValue(Comic.class);

            System.out.println(comic.title + " author: " + comic.author + ", illustrator: " + comic.illustrator + ". I own " + comic.numberOfCopies + ".");

            System.out.println("Uppdating Spider Man Comicbook...");

            spiderMan.illustrator = "Bob Bob";
            UpdateComic updatedComic = new UpdateComic(spiderMan);
            UpdateValue updateValue = new UpdateValue.Builder(spidermanLocation)
                    .withUpdate(updatedComic).build();
            UpdateValue.Response r = client.execute(updateValue);

            System.out.println("Response from server for update: " + r);

            System.out.println("Getting updated comicbook...");

            fetchSpiderManOp = new FetchValue.Builder(spidermanLocation)
                    .build();
            comic = client.execute(fetchSpiderManOp).getValue(Comic.class);

            System.out.println(comic.title + " author: " + comic.author + ", illustrator: " + comic.illustrator + ". I own " + comic.numberOfCopies + ".");


            DeleteValue deleteSpipderManOp = new DeleteValue.Builder(spidermanLocation)
                    .build();
            client.execute(deleteSpipderManOp);
            System.out.println("Spider Man Comicbook deleted");

            System.out.println("Trying to fetch deleted comicbook...");
            fetchSpiderManOp = new FetchValue.Builder(spidermanLocation)
                    .build();
            comic = client.execute(fetchSpiderManOp).getValue(Comic.class);

            System.out.println("Response for fetch: " + comic);

            cluster.shutdown();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}