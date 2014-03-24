
public class BaseNews implements ILoadNews, ISaveNews{
	private ILoadNews loader;
	private ISaveNews saver;
	public News[] LoadNews() {
		return null;
	}
	public void SaveNews() {
		// TODO Auto-generated method stub
	}
	BaseNews(){
		this.loader = null;
		this.saver = null;
	}
	BaseNews(ILoadNews loader, ISaveNews saver){
		this.loader = loader;
		this.saver = saver;
	}
	void setLoader(ILoadNews loader){
		this.loader = loader;
	}
	void setSaver(ISaveNews saver){
		this.saver = saver;
	}
}
