# Burger211
Author: Zach Wilson
Bellevue College CS211 Assignment
Winter 2018

## Overview
This program is used to manage menus for a burger chain. Currently, the program
is only set up to create `Burger` objects and only allows franchise locations
to modify item prices. However, the program is architected in a way that will
allow for easy inclusion of additional menu items and other modification types.

## Architecture
### IMenuItem
The `IMenuItem` interface defines the interface for menu items. Currently, the
only implementation of this interface is the `Burger` class. Additional item
types can be quickly added by implementing this interface, as all other menu
code is set up to receive objects that implement this interface.

### IModifier
The `IModifier` interface is used by Modifier classes - classes that are
designed to modify one or more properties of an item. Currently, the only
Modifier class is the `PriceModifier` class, which is used to modify the
item prices from the standard price to location-specific pricing.

## Input Data
This program reads in two types of input data - **Menu Data** and
**Location Data**. Menu data is stored in the `menu.txt` file and contains
all data necessary to create `IMenuItem`-derived objects. Location data is kept
in location-specific text files (eg. `seattle.txt`, `bellevue.txt`, etc.).
These files contain data necessary to generate `IModifier`-derived objects
that modify objects generated from the Menu data using location-specific
modifications.

### Menu Data
In order to be properly parsed, Menu Data must be specified in the following
order:

* Item Name
* Item Price
* Calorie Count

Should more item types be added, an item type line will be added as the first
line for each menu item. One or more trailing empty lines may be used, but
are not required.

### Item Data
As with Menu Data, Item Data must be properly structured in order for the
program to properly parse and construct objects from the data. Item Data
should be structured as follows:

* Item Name
* Price

If more modifiers are added in the future, a modifier type line will be added
as the first line. Additionally, all lines following the item name line will
become modifier-specific. Empty lines are allowed to be used between each
item's data, but are not required.