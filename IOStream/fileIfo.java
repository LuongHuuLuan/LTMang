package IOStream;

public class fileIfo {
	private String name;
	private long size;
	private long poiter;

	public fileIfo(String name, long size, long poiter) {
		super();
		this.name = name;
		this.size = size;
		this.poiter = poiter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getPoiter() {
		return poiter;
	}

	public void setPoiter(long poiter) {
		this.poiter = poiter;
	}

	@Override
	public String toString() {
		return "fileIfo [name=" + name + ", size=" + size + ", poiter=" + poiter + "]";
	}

}
