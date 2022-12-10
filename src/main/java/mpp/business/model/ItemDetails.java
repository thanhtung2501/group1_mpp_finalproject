package mpp.business.model;

public class ItemDetails {
    private String itemName;
    private boolean highlight = false;

    public ItemDetails(String itemName, boolean highlight) {
        this.itemName = itemName;
        this.highlight = highlight;
    }

    public boolean getHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
