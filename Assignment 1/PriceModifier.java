/******************************************************************************
* PriceModifier.java 
*	The PriceModifier class is designed to modify the price of menu items.
*
******************************************************************************/
public class PriceModifier implements IModifier {
	/// @brief Constructs and initializes the object using the values provided.
	/// @param itemName Name of the item that this object should modify.
	/// @param modifiedPrice Specifies the price used to override the item's
	///		default price.
	public PriceModifier(String itemName, String modifiedPrice) {
		m_itemName = itemName;
		m_modifiedPrice = modifiedPrice;
	}
	/// @brief Returns the name of the item that this modifier is supposed to
	///		modify.
	public String getItemName() {
		return new String(m_itemName);
	}
	/// @brief Applies the modifier to the item.
	public void applyModifier(IMenuItem item) {
		// Verify that the item has the same name as what this modifier is
		// supposed to modify
		assert item.getName().contentEquals(m_itemName);
		// Override the item's price
		item.setPrice(m_modifiedPrice);
	}

	private final String m_itemName;
	private final String m_modifiedPrice;
}