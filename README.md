# SnackAttap


### Usage

A simple snack-selecting application. 

Filter the available snacks with the ***Veggie*** and ***Non-Veggie*** checkboxes above the list.

Add custom snacks of your own using the ***+*** icon in the bar at the top.

Use the ***Submit*** button at the bottom to review your tasty snack choices.

### Notes From the Developer

If I had more time, and/or if this were being developed as a serious production application, I would refactor the snack list manipulating operations into more modular methods and add unit tests for adding and filtering the snack list. I would also implement some kind of dependency injection for the initial list of snacks so that that could be expanded upon easier or possibly changed to a webservice call or something like that; same thing for SnackType. Additionally, more robust error handling would be nice.
