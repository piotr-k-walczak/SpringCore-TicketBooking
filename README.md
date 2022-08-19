# Ticket Booking ( Spring Core )

---

Tutorial project written in **[ 2022 ]**<br/>
The purpose of the project was to learn the basics of Spring Core framework.

---

#### Technologies
- Language: **Java 17**
- Framework: **Spring Core**
- Build: **Maven**
- Testing: **Junit**, **Mockito**

---

#### Description

The project is an backend service enabling users to book tickets for various events. It consists of 3 services **UserService**, **TicketService**, **EventService** enabling all the operations necessary to book or cancel a ticket, and **BookingFacade**. The data is being store in a JSON file. It is loaded from file on app launch using **StorageBeanPostProcessor**.