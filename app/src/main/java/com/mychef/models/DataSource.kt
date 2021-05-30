package com.mychef.models

class DataSource {

    companion object{

        fun createDataSet(): ArrayList<MenuListItems>{
            val list = ArrayList<MenuListItems>()
            list.add(
                MenuListItems(
                    "Cheesy Delight!",
                    "RM 120 per PAX",
                    "https://raw.githubusercontent.com/InaanMohamed/ImagesForMyChef/main/CheesyDelight.jpg?token=AQAFKJX4VVQE3SREYJ74PMDAVT2LS",
                )
            )
            list.add(
                MenuListItems(
                    "Chef's Special",
                    "RM 155 per PAX",
                    "https://raw.githubusercontent.com/InaanMohamed/ImagesForMyChef/main/ChefSpecial.jpg?token=AQAFKJQS6SRB2HTWUNZXR6LAVT2LY",
                )
            )

            list.add(
                MenuListItems(
                    "King Feast",
                    "RM 200 per PAX",
                    "https://raw.githubusercontent.com/InaanMohamed/ImagesForMyChef/main/KingFeast.jpg?token=AQAFKJQ5IZLU3CGR3DTB4ULAVT25W ",
                )
            )
            list.add(
                MenuListItems(
                    "Kids Meal",
                    "RM 100 per PAX",
                    "https://raw.githubusercontent.com/InaanMohamed/ImagesForMyChef/main/KidsMeal.jpg?token=AQAFKJWXLX6WNCNLONID3K3AVT2L6",
                )
            )
            list.add(
                MenuListItems(
                    "Beef Meal",
                    "RM 130 per PAX",
                    "https://raw.githubusercontent.com/InaanMohamed/ImagesForMyChef/main/Beefmealll.jpg?token=AQAFKJRKRWZ7P6OYES6FON3AVT2WK",
                )
            )
            list.add(
                MenuListItems(
                    "Sea Food Lover",
                    "RM 190 per PAX",
                    "https://raw.githubusercontent.com/InaanMohamed/ImagesForMyChef/main/SeaFoodLover.jpg?token=AQAFKJQFEGENU7ZQ6W76YJ3AVT23A",
                )
            )
            return list
        }
    }

}