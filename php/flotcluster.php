<?php

//comment

class histogram {

	private $nBinsX = 0;
	private $nBinsY = 0;
	private $nBins = 0;
	private $minX = 0;
	private $maxX = 0;
	private $minY = 0;
	private $maxY = 0;
	private $binWidthX = 0;
	private $binWidthY = 0;
	private $theHistogram = array();
	
	function __construct($nBinsX, $minX, $maxX, $nBinsY, $minY, $maxY) {
		$this->nBinsX = $nBinsX;
		$this->minX = $minX;
		$this->maxX = $maxX;
		$this->nBinsY = $nBinsY;
		$this->minY = $minY;
		$this->maxY = $maxY;
		$this->binWidthX = ($maxX-$minX)/$nBinsX;
		$this->binWidthY = ($maxY-$minY)/$nBinsY;
		$this->nBins = $nBinsX * $nBinsY;
	}
	
	public function add($x, $y) {
		$iX = floor(($x - $this->minX) / $this->binWidthX);
		$iY = floor(($y - $this->minY) / $this->binWidthY);
		$iBin = intval($iY * $this->nBinsX + $iX);
		//echo $x . " " . $this->minX . " " .  $this->binWidthX . " " . $iX . "\n";
		
		if (array_key_exists($iBin, $this->theHistogram)) {
			array_push($this->theHistogram[$iBin], array($x, $y));
		}
		else {
			$this->theHistogram[$iBin] = array(array($x, $y));
		}
	}
	
	public function jsonEncode() {
		echo json_encode($this->theHistogram);
	}
}

$histogram = new histogram(100, 0, 10, 100, 0, 10);

for($ipoint = 0; $ipoint < 10000; $ipoint++) {
	$x = rand(0,1000)/100;
	$y = rand(0,1000)/100;
	$histogram->add($x, $y);
}

$histogram->jsonEncode();

//echo json_encode ( $data );

?>
