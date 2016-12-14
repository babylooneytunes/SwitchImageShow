package ly.com.imageviewswitchdemo;

public class DataBean
{
    //序号
    private int ID;
    //正文标题
    private String TextTitle;
    //页面说明
    private String pageDesp;

    public DataBean(int ID, String textTitle, String pageDesp)
    {
        this.ID = ID;
        TextTitle = textTitle;
        this.pageDesp = pageDesp;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getTextTitle()
    {
        return TextTitle;
    }

    public void setTextTitle(String textTitle)
    {
        TextTitle = textTitle;
    }

    public String getPageDesp()
    {
        return pageDesp;
    }

    public void setPageDesp(String pageDesp)
    {
        this.pageDesp = pageDesp;
    }
}
