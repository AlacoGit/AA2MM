package com.github.JAA2M.Module;

import java.util.List;


//public record Event(/*DWORD*/ int id, int category, wString name, wString interactiveName, wString description, List<Value.Type> parameters) {
public enum Event{
    INVALID(EventCategories.OTHER,"INVALID","","",List.of()),
    CLOTHES_CHANGED(EventCategories.CHARACTION, "Clothes Changed", "Clothes Changed", "Whenever a character changes clothes, either in the changing room or by entering the next period. (Not yet implemented)", List.of()),
    CARD_INITIALIZED(EventCategories.CARD,"Card Initialized", "Card Initialized", "After the AAU Card data was successfully loaded, either because a class containing this card was loaded, or because \n this card was added to the class roster", List.of()),
    CARD_DESTROYED(EventCategories.CARD, "Card Destroyed", "Card Destroyed", "Before the AAU Card data will be unloaded",List.of()),
    HI_POLY_INIT(EventCategories.LOADS, "Hi Poly Model Initialized", "Hi Poly Model Initialized", "Right after the Characters High Poly Model started loading", List.of()),
    HI_POLY_END(EventCategories.LOADS, "Hi Poly Model Loaded", "Hi Poly Model Loaded", "Right after the Character High Poly Model finished loading", List.of()),
    CARD_ADDED(EventCategories.CARD, "Card Added to Class", "Card Added to Class", "When a card (including this card) is added to a class, after the CARD_INTIALIZED event is executed", List.of()),
    PERIOD_ENDS(EventCategories.TIME, "A Period Ends", "A Period Ends", "After a period ends, including lessions themselves", List.of()),
    NPC_RESPONSE(EventCategories.CONVERSATION, "Npc Answers in a Conversation", "Npc Answers in a Conversation", "Whenever a NPC made a yes/no decision, no matter if towards the PC or another NPC. Triggering Card is the NPC that gives the Answer.", List.of()),
    NPC_WALK_TO_ROOM(EventCategories.CONVERSATION, "Npc Starts Walking to a Room", "Npc Starts Walking to a Room", "Whenever a NPC decides to walk towards a room.", List.of()),
    NPC_WANTS_ACTION_NOTARGET(EventCategories.CONVERSATION, "Npc Wants to do something with no Target", "Npc Wants to do something with no Target", "Whenever a NPC decides to do an action that does not require another Npc.", List.of()),
    NPC_WANT_TALK_WITH(EventCategories.CONVERSATION, "Npc Wants to Talk With Someone", "Npc Wants to Talk With Someone", "Whenever a NPC decides to talk to someone.", List.of()),
    NPC_WANT_TALK_WITH_ABOUT(EventCategories.CONVERSATION, "Npc Wants to Talk With Someone About Someone", "Npc Wants to Talk With Someone About Someone", "Whenever a NPC decides to talk to someone about someone else, such as spreading rumors or asking for opinion about someone.", List.of()),
    PC_CONVERSATION_STATE_UPDATED(EventCategories.CONVERSATION, "PC conversation state updated", "PC Conversation state updated", "Whenever PC conversation has moved on.", List.of()),
    PC_RESPONSE(EventCategories.CONVERSATION, "Pc Answers in a Conversation", "Pc Answers in a Conversation", "Right before the PC answer is processed. Triggering card is the PC", List.of()),
    PC_CONVERSATION_LINE_UPDATED(EventCategories.CONVERSATION, "PC conversation line updated", "PC Conversation line updated", "Whenever NPC speaks a line of dialogue in PC conversation.", List.of()),
    ROOM_CHANGE(EventCategories.CONVERSATION, "Card Changes Room", "Card Changes Room", "Card enters a new room. Works for all cards.", List.of()),
    KEY_PRESS(EventCategories.CONVERSATION, "Key Press", "Key was pressed", "Key was pressed", List.of()),
    HPOSITION_CHANGE(EventCategories.CONVERSATION, "H Position Change", "H Position Change", "The H position changed.", List.of()),
    PC_AFTER_RESPONSE(EventCategories.CONVERSATION, "After PC Response", "After PC Response", "The event is fired after all the triggers have finished editing PC Response.", List.of()),
    NPC_AFTER_RESPONSE(EventCategories.CONVERSATION, "After NPC Response", "After NPC Response", "The event is fired after all the triggers have finished editing NPC Response.", List.of()),
    HI_POLY_DESPAWN(EventCategories.LOADS, "HI Poly Despawn", "HI Poly Despawn", "The event is fired as the character's hi poly is despawning.", List.of()),
    H_END(EventCategories.LOADS, "H End", "H Ends", "The event is fired as the H scene ends.", List.of()),
    H_START(EventCategories.LOADS, "H Start", "H Starts", "The event is fired as the H scene starts.", List.of()),
    CARD_EXPELLED(EventCategories.LOADS, "Card Expelled", "Card Expelled", "The event is fired when a character expelled from class: either killed, arrested or fired.", List.of()),
    CONVERSATION_END(EventCategories.CONVERSATION, "Conversation End", "Conversation End", "The event is fired when a conversation ends between two cards.", List.of()),
    RELATIONSHIP_POINT_CHANGED(EventCategories.CONVERSATION, "Relationship Points Changed", "Relationship Points Changed", "The event is fired just as relationship points are updated between characters.", List.of()),
    DELAYED_EXECUTION(EventCategories.OTHER, "Delayed Execution", "Delayed Execution", "This event is fired from other triggers after a delaay. TriggeringCard is the card whose trigger emitted this event. It does not persist between sessions.", List.of());


    private final EventCategories category;
    public final String name;
    public final String interactiveName;
    public final String description;
    public final List<Value.Types> parameters;

    Event(EventCategories category, String name, String interactiveName, String description, List<Value.Types> parameters){
        this.category = category;
        this.name = name;
        this.interactiveName = interactiveName;
        this.description = description;
        this.parameters = parameters;
    }

    public record ParameterisedEvent(Event event, List<Expression.ParameterisedExpression> actualParameters){
        public String toPrintable(){
            return event.name;
        }
    }
    public static Event fromID(int id){
        if(id < 1 || id > Events.values().length) return null;
        return Event.values()[id-1];
    }
    enum EventCategories/*g_EventCategories*/{
       CARD("Card"),CHARACTION("Character Action"),LOADS("Load"),TIME("Time"),CONVERSATION("Conversation"),OTHER("Other");

        final String name;

        EventCategories(String name) {
            this.name = name;
        }
    }
    enum Events/*g_Events*/{
        INVALID,
        CLOTHES_CHANGED,CARD_INITIALIZED,CARD_DESTROYED,
        HI_POLY_INIT,HI_POLY_END,
        CARD_ADDED,
        PERIOD_ENDS,
        NPC_RESPONSE,
        NPC_WALK_TO_ROOM,NPC_WANTS_ACTION_NOTARGET,NPC_WANT_TALK_WITH,NPC_WANT_TALK_WITH_ABOUT,
        PC_CONVERSATION_STATE_UPDATED,
        PC_RESPONSE,
        PC_CONVERSATION_LINE_UPDATED,
        ROOM_CHANGE,
        KEY_PRESS,
        HPOSITION_CHANGE,
        PC_AFTER_RESPONSE,
        NPC_AFTER_RESPONSE,
        HI_POLY_DESPAWN,
        H_END,
        H_START,
        CARD_EXPELLED,
        CONVERSATION_END,
        RELATIONSHIP_POINT_CHANGED,
        DELAYED_EXECUTION;
    }
    @Override
    public String toString(){
        return this.category+":"+this.name;
    }

}

