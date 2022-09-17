# Notes

Please add here any notes, assumptions and design decisions that might help us understand your thought process.


General plan/thought process:

1) Want the discount scheme to be extensible, so the discounts should be owned by the basket/item list rather than the
   individual item, so we can apply discounts across multiple items (i.e. buy one get one free)
2) All items have a price, however not all would have a discount, therefore we want to make sure the old implementation 
   works in the case of not wanting to add any discounts, so we initialise each item with a NO_DISCOUNT tag by default
3) Logic of the discounts should not be owned by the basket, we want to segregate responsibilities here and keep things
   loosely coupled, so the logic itself should be done by a processor, which owns a list of items
4) We want to be able to apply discounts to every item if wanted, so have just added that field into the interface, 
   keeping the default behaviour as just having no discount 

Hopefully it's readable enough that the rest of the design is somewhat self-evident, cheers for your time!