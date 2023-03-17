public class Event extends EPK_Element{
    private Integer id;
    private String eventText;
    private Integer position;

    public Event(){super();}

    public Event(String eventText) {
        this.eventText = eventText;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return eventText;
    }

    public void setText(String eventText) {
        this.eventText = eventText;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
