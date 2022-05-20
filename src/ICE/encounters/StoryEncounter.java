package ICE.encounters;

public class StoryEncounter implements Encounter{
    private String msg;

    //Constructor
    public StoryEncounter(String msg) {
        this.msg = msg;
    }

    //prints whatever String we add to the queue
    @Override
    public void runGame() {
        System.out.println(msg);
    }
}