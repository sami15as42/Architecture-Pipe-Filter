# Architecture-Pipe-Filter

C'est une application programmée en JAVA pour une vidéothèque (magasin de vente de films, jeux...) implémentée avec une architecture Pipe & Filter. Les filtres sont les classes : GUI (JavaFX), QueryProcessor et TransactionProcessor (GUI | QueryProcessor | TransactionProcessor | GUI... cyclique). Le pipe est implémenté par le biais d'une file de chaines de caractères pour stocker les messages entre filtres et notifier le prochain pour faire son traitement. 

It is an application programmed in JAVA for a video library (store selling films, games ...) implemented with a Pipe & Filter architecture. The filters are the classes: GUI (JavaFX), QueryProcessor and TransactionProcessor (GUI | QueryProcessor | TransactionProcessor | GUI ... cyclic). The pipe is implemented through a queue of strings to store messages between filters and notify the next one to do its processing.
