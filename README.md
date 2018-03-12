# booking

## Build

```
mvn clean install spring-boot:repackage
```

## Run

```
java -jar target\booking-1.0-SNAPSHOT.jar
```

## About

 * `GET /booking?date=2020-01-02`
 * `POST /booking`
 
 
Input example:
```
{
  "start_office_hours": "0900",
  "end_office_hours": "1730",
  "booking_requests": [
  	{
    "submission_time": "2011-03-17 10:17:06",
    "employee_id": "EMP001",
    "meeting_time": "2011-03-21 09:00",
    "duration": 2
  },
  {
    "submission_time": "2011-03-17 10:17:12",
    "employee_id": "EMP002",
    "meeting_time": "2011-03-21 14:00",
    "duration": 2
  },
  {
    "submission_time": "2011-03-17 10:17:13",
    "employee_id": "EMP002",
    "meeting_time": "2011-03-22 14:00",
    "duration": 3
  }]
}
```
 
 Output:
```
{
    "calendar": [
        {
            "day": "2011-03-21",
            "meetings": [
                {
                    "start_time": "09:00",
                    "end_time": "11:00",
                    "employee_id": "EMP001"
                },
                {
                    "start_time": "14:00",
                    "end_time": "16:00",
                    "employee_id": "EMP002"
                }
            ]
        },
        {
             "day": "2011-03-22",
             "meetings": [
                 {
                     "start_time": "14:00",
                     "end_time": "17:00",
                     "employee_id": "EMP002"
                 }
             ]
        }
    ]
}
```

 * No part of a meeting may fall outside office hours
 * Meetings may not overlap
 * New bookings shall not overlap with "old" ones
