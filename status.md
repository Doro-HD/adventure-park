## Status for Coderbois Projektuge

#### How far did we come:
Vi nåede alle de tekniske og programmatiske krav til projektet, dog løb vi ind i tekniske problemer med at håndtere bruger login som desværre gjorde at vi ikke nåede de sidste dele af den user story vi manglede. Vi nåede at få lavet en dynamisk side som dynamisk kan grab og vise vores objekter fra databasen.

#### What doesn't work:
- login, dvs. authentication og authorization virker ikke lige p.t. og dette fører til at de metoder hvor man skal være authorized heller ikke kan tilgås.
- front-end kan være meget langt tid om at fetch data fra back-end da vi kører den gratis løsning.

#### Who did what:
Vi har benyttet os af pair programming til at udvikle programmet så det er lavet i teams.
- David og Lasse
    - Aktiviteter
        - Hente alle aktiviteter fra databasen
        - Opdatere aktiviteter
        - Oprette aktiviteter
        - Slette aktiviteter
    - Sikkerhed
        - Respondere med en json web token når client laver en request til det vilkårlige login endpoint
        - Kunne validere json webtoken når client laver en request til et endpoint som ikke skal være tilgængeligt for alle

- Victor og Troels
    - Vi har arbejdet sammen om vores frontend, da vi følte det var der vi havde størst udfordringer og ville gerne blive bedre til webudvikling.
    Vi opsatte navigo til at kunne route på vores hjemmeside. Vi benyttede Javascript til at fetche data fra vores backend. Vi lavede javascript filer som modules for at give bedre struktur i programmet. Vi har benyttet den utils.js fil Lars har givet til os, til at bruge dens metoder i vores projekt. Vi har også brugt en del bootstrap for at hurtigt kunne implementere noget smart frontend design der er "ready to use".

#### Reflection:
Vi har arbejdet i to opdelte grupper, hvor David/Lasse arbejdede på vores backend og Victor/Troels arbejdede på vores frontend.
Lasse og jeg føler vi godt kunne have tænkt os at blande frontend og backend arbejdet mere mellem de to grupper, så hver gruppe ikke kun tog hver deres ting.
Bortset fra dette har det virket rigtig godt med denne arbejdsform, da vi har specialiseret os i hvert område af programmet.

#### Link to video:
https://drive.google.com/file/d/1DPENs5tCukJonFZXKevYO_DaZk7zSNhb/view?usp=sharing