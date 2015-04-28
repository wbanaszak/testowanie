public class LiczbaRzymska {

    private Integer liczba;

    public LiczbaRzymska(Integer liczba) {
        if (liczba < 0) {
            throw new IllegalArgumentException();
        }
        this.liczba = liczba;
    }

    public Integer getLiczba() {
        return liczba;
    }

    public void setLiczba(Integer liczba) {
        this.liczba = liczba;
    }

    public String toString() {
        int i = 0;

        String wynik = "";
        int arabskie[] = {1000, 500, 100, 50, 10, 5, 1};
        char rzymskie[] = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        final int ileRzymskich = arabskie.length;

        if ((liczba > 3999) || (liczba <= 0))
        {
            return wynik;
        }

        while ((liczba > 0) && (i < ileRzymskich))
        {
            if(liczba >= arabskie[i])
            {
                liczba -= arabskie[i];
                wynik += rzymskie[i];
            }
            else if ((i%2 == 0) &&
                    (i<ileRzymskich-2) && // 9xx condition
                    (liczba >= arabskie[i] - arabskie[i+2]) &&
                    (arabskie[i+2] != arabskie[i] - arabskie[i+2]))
            {
                liczba -= arabskie[i] - arabskie[i+2];
                wynik += rzymskie[i+2];
                wynik += rzymskie[i];
                i++;
            }
            else if ((i%2 == 1) &&
                    (i<ileRzymskich-1) && //4xx condition
                    (liczba >= arabskie[i] - arabskie[i+1]) &&
                    (arabskie[i+1] != arabskie[i] - arabskie[i+1]))
            {
                liczba -= arabskie[i] - arabskie[i+1];
                wynik += rzymskie[i+1];
                wynik += rzymskie[i];
                i++;
            }
            else
            {
                i++;
            }
        }

        return wynik;
    }
}
