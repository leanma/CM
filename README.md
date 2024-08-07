# CriptoMarket
This project, developed in Microsoft SQL Server and written in Spanish, aims to manage cryptocurrency trading and market management. It implements object-oriented programming concepts, featuring classes for users, admins, cryptocurrencies, and markets. Users can buy, sell, and manage cryptocurrencies, ensuring data consistency and integrity.

Objective
Implement all concepts of Object-Oriented Programming as a solution to a real-world problem, starting from problem analysis, designing the solution (Class Diagram), and subsequently building the final product (Software).

Procedure
Research and Documentation
Cryptocurrencies (cryptocurrencies or virtual currencies) refer to money existing in a digital environment. They are widely used for exchanges between businesses or institutions. Cryptocurrency values are variable, changing based on market demand and supply, as well as user engagement. Cryptocurrencies are used in a decentralized network of computers known as blockchain, composed of nodes distributed worldwide.

Key Concepts:

Nodes: Computers involved in the blockchain network. People who operate or control these nodes are known as miners.
Exchanges: Companies that facilitate the exchange of fiat currencies (like dollars or euros) for cryptocurrencies.
Important Cryptocurrencies:

Bitcoin (BTC): The pioneering and highest-value cryptocurrency in the market.
Ethereum (ETH): A blockchain platform that enables running applications within its network, powered by its cryptocurrency, ether.
Dogecoin (DOGE): A virtual currency derived from Litecoin, famous on social media.
Cardano (ADA): A smart contract platform with a layered architecture, using the ADA cryptocurrency.
Ripple (XRP): Known for its transaction speed.
Tether (USDT): Known as a stablecoin, backed by an equivalent amount in dollars, euros, or yen.
Litecoin (LTC): Noted for its liquidity and payment speed.
Dash: Known for reduced cost and transaction time, enhancing user privacy.
Constellation (DAG): A decentralized blockchain driven by microservices.
Safemoon: A decentralized finance (DeFi) token.
Development
Here is a detailed description of the functionalities and contributions of the classes involved in the project:

App:
The main class of the cryptocurrency management system, allowing users to interact with cryptocurrencies and markets. Functions:

Declaration of global variables.
File initialization.
User login and verification.
User interaction.
Data saving.
File:
Utility class for file management. Functions:

Loading, creating, and saving files.
Checking file existence.
CryptoCurrency:
Represents a cryptocurrency within the system. Functions:

Getters and setters.
Utility methods.
Main:
Entry point for executing the system.

Market:
Represents a market for a cryptocurrency, providing details on its symbol, capacity, transaction volume, and variation. Functions:

Getters and setters.
Utility methods.
MyException:
Class extending Exception to handle specific errors with custom messages.

User:
Abstract class defining basic attributes and behaviors of a user. Serves as a base for UserAdmin and UserTrader. Common methods for both types of users.

UserAdmin:
Extends User with administrative privileges. Functions:

Create, modify, and delete cryptocurrencies.
Query cryptocurrencies and market status.
UserTrader:
Extends User, specialized in cryptocurrency trading. Functions:

Buy and sell cryptocurrencies.
Query and recommend cryptocurrencies.
Query market status and view transaction history.
Conclusion
The project offers a comprehensive application for buying, selling, and managing a market and its cryptocurrencies by implementing various Object-Oriented Programming concepts such as inheritance, polymorphism, encapsulation, and abstract classes. Traders and administrators interact to perform specific system functionalities. Through an interactive interface, users can execute various operations, query market information, and maintain a detailed transaction history.
