/******************************************************************************
* IModifier.java 
*	Defines an interface used by "Modifier" objects; objects that change the
*	properties of menu items to fit a franchise location.
*
******************************************************************************/
public interface IModifier {
	/// @brief Returns the name of the item that this modifier is supposed to
	///		modify.
	/// @details Modifiers are only invoked on items that have the same name
	///		as the item name returned by this function.
	String getItemName();
	/// @brief Applies the modifier to the item.
	void applyModifier(IMenuItem item);
}