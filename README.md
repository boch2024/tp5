# Applications reparties : Java RMI

Ce depot contient deux travaux pratiques realises avec Java RMI :

- TP 5.1 : service de conversion de devises (EUR vers TND)
- TP 5.2 : gestion de comptes bancaires avec enregistrement via JNDI

## Prerequis

- JDK >= 1.8
- VS Code avec Extension Pack for Java (ou Eclipse / IntelliJ)
- Git

---

## TP 5.1 : Service de conversion de devises

Un serveur RMI offre aux clients la possibilite de convertir un montant en euros vers le dinar tunisien.

### Structure

- RMIConversion_Server
  - rmiService.IConversion : interface distante `double convertirMontant(double mt)`
  - rmiService.ConversionImpl : implementation du service
  - rmiServer.ConversionServer : active le RMIRegistry, cree l'objet distant et le publie dans l'annuaire
- RMIConversion_Client
  - rmiClient.ConversionClient : recherche la reference dans l'annuaire et invoque `convertirMontant(500)`
- conversionClient.jar : contient uniquement l'interface `IConversion`, ajoute au classpath du client

### Execution

1. Lancer `ConversionServer` (il doit rester actif).
2. Lancer `ConversionClient`.

### Capture d'ecran : execution du client

<img width="1190" height="411" alt="image" src="https://github.com/user-attachments/assets/4293b030-474e-4703-8bbd-397b04a92be5" />


### Resultat

```
Montant initial : 500.0 EUR
Montant converti : 1675.0 TND
```

Taux utilise : 1 EUR = 3.35 TND.

---

## TP 5.2 : Gestion de comptes bancaires (RMI + JNDI)

Un serveur RMI central gere les comptes bancaires. Un compte est defini par un code (int), un solde (double) et une date de creation (Date). Le serveur offre deux services distants :

- creer un compte avec un code et une somme initiale (si le code n'existe pas deja)
- consulter un compte a partir de son code

### Structure

- RMIBanque_Server
  - metier.Compte : classe serializable
  - rmiService.IBanque : interface distante `String creerCompte(Compte c)` et `String getInfoCompte(int code)`
  - rmiService.BanqueImpl : implementation du service
  - rmiServer.BanqueServer : cree le registre RMI et publie `BanqueImpl` via JNDI
  - jndi.properties : dans le dossier `src`
- RMIBanque_Client
  - rmiClient.BanqueClient : recherche `IBanque` via JNDI et invoque les methodes distantes
  - jndi.properties : dans le dossier `src`
- banqueClient.jar : contient uniquement `IBanque` et `Compte`

### Configuration JNDI

Fichier `jndi.properties` place directement dans `src` du serveur et du client :

```
java.naming.factory.initial=com.sun.jndi.rmi.registry.RegistryContextFactory
java.naming.provider.url=rmi://localhost:1099
```

Le serveur utilise `new InitialContext().rebind("banque", obj)` et le client `new InitialContext().lookup("banque")`.

