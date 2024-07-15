package com.example.server.mock.idpos2.device;

import java.util.*;

public class Device {

    private static final Map<State, Set<State>> transitionsMap = Map.of(
            State.unknown, Set.of(State.idle),
            State.idle, Set.of(State.scanning, State.idle),
            State.scanning, Set.of(State.idle, State.scanning)
    );

    private static final List<RfidTag> tags = List.of(
            new RfidTag("FFFC7A1230001BF84BC77670", "urn:epc:id:sgtin:2000012.030267.241789531760", "(01)02000012302671(21)241789531760"),
            new RfidTag("30349a4ebc02558380f7032119111", "", ""),
            new RfidTag("303499f13015f198ee400151", "", ""),
            new RfidTag("303499f13015f198ee40015", "", "")
    );

    private final String name = "IDPOS2_MOCK";

    private final String macAddress = "00:00:00:00:00:00";

    private final String systemId = "7e1d6794-7e8d-44c2-9d83-90558f54bb4d";

    private State currentState = State.unknown;

    private int scanId = 0;

    private int scanCount = 0;

    private Random rand = new Random();

    public boolean moveToState(State newState){
        Set<State> possibleStates = transitionsMap.get(currentState);
        if (possibleStates != null && possibleStates.contains(newState)){
            this.currentState = newState;
            return true;
        }

        return false;
    }

    public RfidTag scanTag(){
        ++scanCount;
        return getRandTag();
    }

    public RfidTag getRandTag(){
        int index = rand.nextInt(0,tags.size());
        return tags.get(index);
    }

    public RfidTag getTag(int index){
        return tags.get(index);
    }

    public State getCurrentState() {
        return currentState;
    }

    public String getName() {
        return name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getSystemId() {
        return systemId;
    }

    public List<RfidTag> scanAllTags(){
        return tags;
    }

    public int startScan(){
        scanCount = 0;
        return ++this.scanId;
    }

    public int stopScan(int scanId){
        return scanId == this.scanId ? scanCount : -1;
    }

    public boolean matchScanId(int scanId){
        return this.scanId == scanId;
    }

    public void reset(){
        this.currentState = State.unknown;
        this.scanId = 0;
        this.scanCount = 0;
    }
}
