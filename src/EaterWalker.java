
public class EaterWalker extends Agent {

	public EaterWalker( int __x, int __y, World __w )
	{
		super(__x,__y,__w);
	}
	
	public void step( )
	{
		// met a jour: (1) la couleur du sol (2) l'orientation de l'agent
		
		int cellColor[] = _world.getCellState(_x, _y);
		
		cellColor[redId]   = 255;
		cellColor[greenId] = 255;
		cellColor[blueId]  = 255;

		_world.setCellState(_x, _y, cellColor);

		if ( Math.random() > 0.5 ) // au hasard
			_orient = (_orient+1) %4;
		else
			_orient = (_orient-1+4) %4;

		
		// met a jour: la position de l'agent (dépend de l'orientation)
		 switch ( _orient ) 
		 {
         	case 0: // nord	
         		_y = ( _y - 1 + _world.getHeight() ) % _world.getHeight();
         		break;
         	case 1:	// est
         		_x = ( _x + 1 + _world.getWidth() ) % _world.getWidth();
 				break;
         	case 2:	// sud
         		_y = ( _y + 1 + _world.getHeight() ) % _world.getHeight();
 				break;
         	case 3:	// ouest
         		_x = ( _x - 1 + _world.getWidth() ) % _world.getWidth();
 				break;
		 }
	}
	
}
