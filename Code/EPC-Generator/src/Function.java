public class Function extends EPK_Element{

    private Integer id;
    private String Text;
    private Integer position;

    public Function()
    {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return Text;
    }

    public void setText(String functionText) {
        this.Text = functionText;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

}
