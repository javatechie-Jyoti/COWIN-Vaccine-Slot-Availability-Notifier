#CoWIN APIs

1. To get all the appointments(available and booked) in District for the next 7 days from today,

curl -X GET "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=149&date=05-05-2021" -H "accept: application/json" -H "Accept-Language: hi_IN" -H "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36"


2. To get all the available appointments in District for a particular date:

curl -X GET "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id=149&date=12-05-2021" -H "accept: application/json" -H "Accept-Language: hi_IN" -H "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36"