public class PredatorAgent extends Agent {

    static double p_reproduce = 0.04;
    static int delai_de_famine = 23;
    boolean _predator;
    boolean _alive;
    int it_non_mange;

    public PredatorAgent(int __x, int __y, World __w) {
        super(__x, __y, __w);

        _alive = true;
        _predator = true;
        it_non_mange = 0;
    }

    public void reset_mange() {
        it_non_mange = 0;
    }

    public boolean isAlive() {
        return _alive;
    }
    public void step() {
        // met a jour l'agent

        // A COMPLETER
        // reproduire
        it_non_mange++;
        if (it_non_mange > delai_de_famine){
            _alive = false;
            return;
        }
        if (Math.random() < p_reproduce) {
            _world.add(new PredatorAgent(_x, _y, _world));
        }
        int[] cellColor = _world.getCellState(_x, _y);

        cellColor[redId] = 255;
        cellColor[greenId] = 240;
        cellColor[blueId] = 225;

        _world.setCellState(_x, _y, cellColor);

        if (Math.random() > 0.5) // au hasard
            _orient = (_orient + 1) % 4;
        else
            _orient = (_orient - 1 + 4) % 4;

        // met a jour: la position de l'agent (depend de l'orientation)
        switch (_orient) {
// nord
            case 0 -> _y = (_y - 1 + _world.getHeight()) % _world.getHeight();
// est
            case 1 -> _x = (_x + 1 + _world.getWidth()) % _world.getWidth();
// sud
            case 2 -> _y = (_y + 1 + _world.getHeight()) % _world.getHeight();
// ouest
            case 3 -> _x = (_x - 1 + _world.getWidth()) % _world.getWidth();
        }
    }

}
