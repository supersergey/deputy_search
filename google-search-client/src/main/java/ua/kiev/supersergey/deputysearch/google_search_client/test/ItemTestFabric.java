package ua.kiev.supersergey.deputysearch.google_search_client.test;

public class ItemTestFabric {
    private static int counter = 0;

    public static ItemTest getNewItem() throws Exception{
        if (counter == 10) {
            throw new RuntimeException();
        } else {
            return new ItemTest(++counter);
        }
    }
}
