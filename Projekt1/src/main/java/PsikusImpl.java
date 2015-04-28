import java.util.Random;

public class PsikusImpl implements Psikus {

    private Random generator = new Random();

    @Override
    public Integer cyfrokrad(Integer liczba) {
        String liczbaString = liczba.toString();
        if (liczbaString.length() == 1) {
            return null;
        }
        int index = generator.nextInt(liczbaString.length());
        return Integer.parseInt(liczbaString.substring(0, index) + liczbaString.substring(index + 1));
    }

    @Override
    public Integer hultajchochla(Integer liczba) throws NieduanyPsikusException {
        String liczbaString = liczba.toString();
        if (liczbaString.length() == 1) {
            throw new NieduanyPsikusException();
        }
        int index = generator.nextInt(liczbaString.length());
        int index2 = generator.nextInt(liczbaString.length());
        char i1 = liczbaString.charAt(index);
        char i2 = liczbaString.charAt(index2);

        char[] tab = liczbaString.toCharArray();
        tab[index] = i2;
        tab[index2] = i1;
        return Integer.parseInt(String.valueOf(tab));
    }

    @Override
    public Integer nieksztaltek(Integer liczba) {
        String liczbaString = liczba.toString();
        int c = generator.nextInt(3);
        switch (c) {
            case 0:
                liczbaString = liczbaString.replace('3', '8');
                break;
            case 1:
                liczbaString = liczbaString.replace('7', '1');
                break;
            case 2:
                liczbaString = liczbaString.replace('6', '9');
                break;
        }
        return Integer.parseInt(liczbaString);
    }
}
