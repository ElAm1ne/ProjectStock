Ce projet est developpé en <b>Java</b> en utilisant le <b>Spring</b> framework en utilisant les technologies <b>Spring Boot</b>, <b>Spring data JPA</b>, <b>Lombok</b>. 
Il s'articule autour de l'architecture microservice avec 3 couches (<b>Model, Service, Controller</b>).

Le projet permet deux fonctionnalités principales :
- La <b>consultation du cours d'une action</b> à partir de son ticker et une date de début, une date de fin.
- Un <b>système d'audit des recherches réalisées sur le site</b>, le stock le plus recherché sur une période etc...

D'autres fonctionnalités en découle comme <b>un moteur de backtesting</b> de portefeuille sur une période, et des présentations avec des graphes sous forme de <b>CandleStick Charts</b>.

L'hebergement complet (BDD + Backend) sont fait chez le fournisseur <b>cloud Azure</b> sous forme d'une BDD MySQL et une web app service.

Le projet est en <b>CI-CD</b> en utilisant <b>GitHub Actions</b> et l'intégration <b>Azure</b>, un script de build et deploiement du Jar existe dans le repository et est lancé à chaque push dans la branche master.

<b>Quelques requêtes à l'API backend : </b>
<li>Accéder à l'historique du cours d'<b>Apple</b> au 14/11/2022 :</li> 
<strong><a href="https://projectstockif.azurewebsites.net/api/stocks/stockBetween?ticker=AAPL&amp;start=2022-11-14&amp;end=2022-11-14" target="_blank">https://projectstockif.azurewebsites.net/api/stocks/stockBetween?ticker=AAPL&amp;start=2022-11-14&amp;end=2022-11-14</a></strong>
<li><b>Backtest</b> d'un portefeuille composé de <b>Apple, Amazon et Google avec des pourcentages de 50%, 20%, 30%</b> respectivement entre le <b>01/04/2023 et le 11/01/2023</b> :</li>
<strong><a href="https://projectstockif.azurewebsites.net/api/stocks/backtest?valptf=10000&amp;tickers=AAPL,AMZN,GOOG&amp;percentages=0.5,0.2,0.3&amp;start=2023-01-04&amp;end=2023-01-11" target="_blank">https://projectstockif.azurewebsites.net/api/stocks/backtest?valptf=10000&amp;tickers=AAPL,AMZN,GOOG&amp;percentages=0.5,0.2,0.3&amp;start=2023-01-04&amp;end=2023-01-11</a></strong>
