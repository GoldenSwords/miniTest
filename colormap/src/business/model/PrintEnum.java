package business.model;

/**
 * 文件输出类型
 */
public enum PrintEnum {
//	KML(0,0),
	PNG(737,920),
	PNGGRID(1737,1790);
//	PNG(2000,1100);//输出图片像素
//	JSON(0,0);
	//自定义参数
	private int x;
	private int y;
	PrintEnum(int x,int y) {
		this.x=x;
		this.y=y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
