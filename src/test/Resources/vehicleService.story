Meta:
Narrative:
As a user when I get it to read files, the service to be able to enter a vehicle registration number and get
the correct make and colour of the cars from Website

Scenario Outline: The user has files to read by the service in directory testfiles1
Given the service has <number> files to read in directory <path>
When the service is called
And the registration number <plate> from <file> is entered into the website
Then the website details will match the service with make of AUDI with a colour of WHITE
Examples:
| number | path       | plate   | file      |
| 1      | testfiles1 | EN17BYJ | file1.csv |

