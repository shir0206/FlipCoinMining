package entity;

public class RiddleLevel {

	private final int levelCode;
	private String levelName;
	private int levelDifficulty;
	private int blockSize;

	public RiddleLevel(int levelCode, String levelName, int levelDifficulty, int blockSize) {
		super();
		this.levelCode = levelCode;
		this.levelName = levelName;
		this.levelDifficulty = levelDifficulty;
		this.blockSize = blockSize;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getLevelDifficulty() {
		return levelDifficulty;
	}

	public void setLevelDifficulty(int levelDifficulty) {
		this.levelDifficulty = levelDifficulty;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getLevelCode() {
		return levelCode;
	}

	@Override
	public String toString() {
		return "RiddleLevel [levelCode=" + levelCode + ", levelName=" + levelName + ", levelDifficulty="
				+ levelDifficulty + ", blockSize=" + blockSize + "]";
	}

}
