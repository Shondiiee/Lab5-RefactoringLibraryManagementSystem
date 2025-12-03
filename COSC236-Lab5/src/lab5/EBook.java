package lab5;

public class EBook implements Book {
    private String title;
    private boolean isAvailable;
    
    public EBook(String title) {
        this.title = title;
        this.isAvailable = true;
    }
    
    @Override
    public String getTitle() {
        return title;
    }
    
    @Override
    public String toString() {
        return "EBook: " + title;
    }
    
    @Override
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    @Override
    public boolean getIsAvailable() {
        return isAvailable;
    }
}