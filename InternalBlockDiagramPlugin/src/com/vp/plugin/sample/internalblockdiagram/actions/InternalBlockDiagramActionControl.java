package com.vp.plugin.sample.internalblockdiagram.actions;

import java.awt.Point;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.DiagramManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.diagram.IConnectorUIModel;
import com.vp.plugin.diagram.IDiagramTypeConstants;
import com.vp.plugin.diagram.IInternalBlockDiagramUIModel;
import com.vp.plugin.diagram.IShapeTypeConstants;
import com.vp.plugin.diagram.shape.ISysMLFlowPortUIModel;
import com.vp.plugin.diagram.shape.ISysMLItemFlowUIModel;
import com.vp.plugin.diagram.shape.ISysMLPartPropertyUIModel;
import com.vp.plugin.diagram.shape.ISysMLPropertyUIModel;
import com.vp.plugin.model.IAttribute;
import com.vp.plugin.model.IConnector;
import com.vp.plugin.model.IPort;
import com.vp.plugin.model.ISysMLBlock;
import com.vp.plugin.model.ISysMLFlowPort;
import com.vp.plugin.model.ISysMLItemFlow;
import com.vp.plugin.model.factory.IModelElementFactory;

public class InternalBlockDiagramActionControl implements VPActionController {

	@Override
	public void performAction(VPAction arg0) {
		// Create parent block for the diagram
		ISysMLBlock blkPowerSubsystem = IModelElementFactory.instance().createSysMLBlock();
		blkPowerSubsystem.setName("PowerSubsystem");
		
		// Create internal block diagram and make it as child (sub-diagram) of the PowerSubsystem block
		DiagramManager diagramManager = ApplicationManager.instance().getDiagramManager();
		IInternalBlockDiagramUIModel diagram = (IInternalBlockDiagramUIModel) diagramManager.createDiagram(IDiagramTypeConstants.DIAGRAM_TYPE_INTERNAL_BLOCK_DIAGRAM);
		diagram.setName("Fuel Distribution Details");		
		blkPowerSubsystem.addSubDiagram(diagram);
		
		// Create blocks FuelTankAssy, Fuel, FuelPump, InternalCombustionEngine, 
		// FuelInjector, FuelRail and FuelFegulator
		ISysMLBlock blkFuelTankAssy = IModelElementFactory.instance().createSysMLBlock();
		blkFuelTankAssy.setName("FuelTankAssy");

		ISysMLBlock blkFuel = IModelElementFactory.instance().createSysMLBlock();
		blkFuel.setName("Fuel");
		
		ISysMLBlock blkFuelPump = IModelElementFactory.instance().createSysMLBlock();
		blkFuelPump.setName("FuelPump");		
				
		ISysMLBlock blkInternalCombustionEngine = IModelElementFactory.instance().createSysMLBlock();
		blkInternalCombustionEngine.setName("InternalCombustionEngine");
		
		ISysMLBlock blkFuelInjector = IModelElementFactory.instance().createSysMLBlock();
		blkFuelInjector.setName("FuelInjector");
		
		ISysMLBlock blkFuelRail = IModelElementFactory.instance().createSysMLBlock();
		blkFuelRail.setName("FuelRail");
		
		ISysMLBlock blkFuelRegulator = IModelElementFactory.instance().createSysMLBlock();
		blkFuelRegulator.setName("FuelRegulator");
		
		// Create part property for FuelTankAssy under PowerSubsystem
		IAttribute attrFuelTankAssy = blkPowerSubsystem.createAttribute();
		attrFuelTankAssy.setName("ft");
		attrFuelTankAssy.setType(blkFuelTankAssy);
		
		// Create diagram element for FuelTankAssy
		ISysMLPropertyUIModel partFuelTankAssy = (ISysMLPropertyUIModel) diagramManager.createDiagramElement(diagram, IShapeTypeConstants.SHAPE_TYPE_SYS_ML_PROPERTY);
		partFuelTankAssy.setModelElement(attrFuelTankAssy);
		partFuelTankAssy.setBounds(100, 400, 350, 130);
		partFuelTankAssy.setRequestResetCaption(true);				
		
		// Create part property Fuel under FuelTankAssy
		IAttribute attrFuel = attrFuelTankAssy.createAttribute();
		attrFuel.setName("fuel");
		attrFuel.setType(blkFuel);
		
		// Create diagram element for Fuel
		ISysMLPropertyUIModel partFuel = (ISysMLPropertyUIModel) diagramManager.createDiagramElement(diagram, IShapeTypeConstants.SHAPE_TYPE_SYS_ML_PROPERTY);
		partFuel.setModelElement(attrFuel);
		partFuel.setBounds(120, 450, 100, 40);
		partFuelTankAssy.addChild(partFuel);
		partFuel.setRequestResetCaption(true);	
		
		// Create part property FuelPump under FuelTankAssy
		IAttribute attrFuelPump = attrFuelTankAssy.createAttribute();
		attrFuelPump.setName("fp");
		attrFuelPump.setType(blkFuelPump);
		
		// Create diagram element for FuelPump
		ISysMLPropertyUIModel partFuelPump = (ISysMLPropertyUIModel) diagramManager.createDiagramElement(diagram, IShapeTypeConstants.SHAPE_TYPE_SYS_ML_PROPERTY);
		partFuelPump.setModelElement(attrFuelPump);
		partFuelPump.setBounds(270, 450, 100, 40);
		partFuelTankAssy.addChild(partFuelPump);
		partFuelPump.setRequestResetCaption(true);		
		
		// Create outgoing port to FuelPump
		ISysMLFlowPort fuelPumpOutPortModel = attrFuelPump.createSysMLFlowPort();
		fuelPumpOutPortModel.setDirection(ISysMLFlowPort.DIRECTION_OUT);
		
		// Create diagram element for outgoing port on FuelPump
		ISysMLFlowPortUIModel fuelPumpOutPortShape = (ISysMLFlowPortUIModel) diagramManager.createDiagramElement(diagram, fuelPumpOutPortModel);
		partFuelPump.addChild(fuelPumpOutPortShape);
		fuelPumpOutPortShape.setBounds(365, 465, 15, 15);
		
		// Create two out port on FuelTankAssy
		ISysMLFlowPort fuelTankAssyOutPortModel1 = attrFuelTankAssy.createSysMLFlowPort();
		fuelTankAssyOutPortModel1.setName("p1");
		fuelTankAssyOutPortModel1.setType(blkFuel);
		fuelTankAssyOutPortModel1.setDirection(ISysMLFlowPort.DIRECTION_OUT);			
		
		ISysMLFlowPort fuelTankAssyOutPortModel2 = attrFuelTankAssy.createSysMLFlowPort();
		fuelTankAssyOutPortModel2.setName("p2");
		fuelTankAssyOutPortModel2.setType(blkFuel);
		fuelTankAssyOutPortModel2.setDirection(ISysMLFlowPort.DIRECTION_OUT);
		
		// Create diagram element for the two outgoing port on FuelTankAssy
		ISysMLFlowPortUIModel fuelTankAssyOutPortShape1 = (ISysMLFlowPortUIModel) diagramManager.createDiagramElement(diagram, fuelTankAssyOutPortModel1);
		partFuelTankAssy.addChild(fuelTankAssyOutPortShape1);
		fuelTankAssyOutPortShape1.setBounds(445, 465, 15, 15);
		fuelTankAssyOutPortShape1.setRequestResetCaption(true);

		ISysMLFlowPortUIModel fuelTankAssyOutPortShape2 = (ISysMLFlowPortUIModel) diagramManager.createDiagramElement(diagram, fuelTankAssyOutPortModel2);
		partFuelTankAssy.addChild(fuelTankAssyOutPortShape2);
		fuelTankAssyOutPortShape2.setBounds(445, 505, 15, 15);
		fuelTankAssyOutPortShape2.setRequestResetCaption(true);
		
		// Create connector between outgoing port of FuelPump and FuelTankAssy
		IConnector connectorFuelPumpFuelTankAssy = IModelElementFactory.instance().createConnector();
		connectorFuelPumpFuelTankAssy.setFrom(fuelPumpOutPortModel);
		connectorFuelPumpFuelTankAssy.setTo(fuelTankAssyOutPortModel1);		
		diagramManager.createConnector(diagram, connectorFuelPumpFuelTankAssy, fuelPumpOutPortShape, 
										fuelTankAssyOutPortShape1, new Point[] {new Point(380,472), new Point(445,472)} );
		
		// Create connector between fuel and outgoing port of FuelTankAssy
		IConnector connectorFuelFuelTankAssy = IModelElementFactory.instance().createConnector();
		connectorFuelFuelTankAssy.setFrom(attrFuel);
		connectorFuelFuelTankAssy.setTo(fuelTankAssyOutPortModel2);		
		diagramManager.createConnector(diagram, connectorFuelFuelTankAssy, 
									partFuel, fuelTankAssyOutPortShape2, 
									new Point[] {new Point(170,490), new Point(170,510), new Point(445,510)} );
				
		// Create part property InternalCombustionEngine
		IAttribute attrInternalCombustionEngine = blkPowerSubsystem.createAttribute();
		attrInternalCombustionEngine.setName("ice");
		attrInternalCombustionEngine.setType(blkInternalCombustionEngine);
		
		// Create diagram element for InternalCombustionEngine
		ISysMLPropertyUIModel partInternalCombustionEngine = (ISysMLPropertyUIModel) diagramManager.createDiagramElement(diagram, IShapeTypeConstants.SHAPE_TYPE_SYS_ML_PROPERTY);
		partInternalCombustionEngine.setModelElement(attrInternalCombustionEngine);
		partInternalCombustionEngine.setBounds(550, 100, 390, 290);
		partInternalCombustionEngine.setRequestResetCaption(true);				

		// Create outgoing port to InterenalCombustionEngine
		ISysMLFlowPort engineInOutPortModel = attrInternalCombustionEngine.createSysMLFlowPort();
		engineInOutPortModel.setDirection(ISysMLFlowPort.DIRECTION_INOUT);
		engineInOutPortModel.setName("fuelFitting");
		engineInOutPortModel.setType(blkFuel);
		
		// Create diagram element for outgoing port on InterenalCombustionEngine
		ISysMLFlowPortUIModel engineInOutPortShape = (ISysMLFlowPortUIModel) diagramManager.createDiagramElement(diagram, engineInOutPortModel);
		partInternalCombustionEngine.addChild(engineInOutPortShape);
		engineInOutPortShape.setBounds(830, 385, 15, 15);
		engineInOutPortShape.setRequestResetCaption(true);			

		// Create part property FuelInjector1 under InternalCombustionEngine
		IAttribute attrFuelInjector1 = attrFuelTankAssy.createAttribute();
		attrFuelInjector1.setName("fi1");
		attrFuelInjector1.setType(blkFuelInjector);
		
		// Create diagram element for FuelInjector1
		ISysMLPropertyUIModel partFuelInjector1 = (ISysMLPropertyUIModel) diagramManager.createDiagramElement(diagram, IShapeTypeConstants.SHAPE_TYPE_SYS_ML_PROPERTY);
		partFuelInjector1.setModelElement(attrFuelInjector1);
		partFuelInjector1.setBounds(760, 140, 150, 40);
		partInternalCombustionEngine.addChild(partFuelInjector1);
		partFuelInjector1.setRequestResetCaption(true);	

		// Create incoming port to FuelInjector1
		ISysMLFlowPort fuelInjector1InPortModel = attrFuelPump.createSysMLFlowPort();
		fuelInjector1InPortModel.setDirection(ISysMLFlowPort.DIRECTION_IN);
		
		// Create diagram element for incoming port on FuelInjector1
		ISysMLFlowPortUIModel fuelInjector1InPortShape = (ISysMLFlowPortUIModel) diagramManager.createDiagramElement(diagram, fuelInjector1InPortModel);
		partFuelInjector1.addChild(fuelInjector1InPortShape);
		fuelInjector1InPortShape.setBounds(755, 150, 15, 15);
		fuelInjector1InPortShape.setRequestResetCaption(true);			
		
		// Create part property FuelInjector2 under InternalCombustionEngine
		IAttribute attrFuelInjector2 = attrFuelTankAssy.createAttribute();
		attrFuelInjector2.setName("fi2");
		attrFuelInjector2.setType(blkFuelInjector);
		
		// Create diagram element for FuelInjector2
		ISysMLPropertyUIModel partFuelInjector2 = (ISysMLPropertyUIModel) diagramManager.createDiagramElement(diagram, IShapeTypeConstants.SHAPE_TYPE_SYS_ML_PROPERTY);
		partFuelInjector2.setModelElement(attrFuelInjector2);
		partFuelInjector2.setBounds(760, 210, 150, 40);
		partInternalCombustionEngine.addChild(partFuelInjector2);
		partFuelInjector2.setRequestResetCaption(true);	

		// Create incoming port to FuelInjector2
		ISysMLFlowPort fuelInjector2InPortModel = attrFuelPump.createSysMLFlowPort();
		fuelInjector2InPortModel.setDirection(ISysMLFlowPort.DIRECTION_IN);
		
		// Create diagram element for incoming port on FuelInjector2
		ISysMLFlowPortUIModel fuelInjector2InPortShape = (ISysMLFlowPortUIModel) diagramManager.createDiagramElement(diagram, fuelInjector2InPortModel);
		partFuelInjector2.addChild(fuelInjector2InPortShape);
		fuelInjector2InPortShape.setBounds(755, 220, 15, 15);
		fuelInjector2InPortShape.setRequestResetCaption(true);			
		
		// Create part property FuelRegulator under InternalCombustionEngine
		IAttribute attrFuelRegulator = attrFuelTankAssy.createAttribute();
		attrFuelRegulator.setName("regulator");
		attrFuelRegulator.setType(blkFuelRegulator);
		
		// Create diagram element for FuelRegulator
		ISysMLPropertyUIModel partFuelRegulator = (ISysMLPropertyUIModel) diagramManager.createDiagramElement(diagram, IShapeTypeConstants.SHAPE_TYPE_SYS_ML_PROPERTY);
		partFuelRegulator.setModelElement(attrFuelRegulator);
		partFuelRegulator.setBounds(760, 280, 150, 40);
		partInternalCombustionEngine.addChild(partFuelRegulator);
		partFuelRegulator.setRequestResetCaption(true);		

		// Create outgoing port to FuelRegulator
		ISysMLFlowPort fuelRegulatorOutPort1Model = attrFuelPump.createSysMLFlowPort();
		fuelRegulatorOutPort1Model.setDirection(ISysMLFlowPort.DIRECTION_OUT);
		
		// Create diagram element for outgoing port on FuelRegulator
		ISysMLFlowPortUIModel fuelRegulatorOutPort1Shape = (ISysMLFlowPortUIModel) diagramManager.createDiagramElement(diagram, fuelRegulatorOutPort1Model);
		partFuelRegulator.addChild(fuelRegulatorOutPort1Shape);
		fuelRegulatorOutPort1Shape.setBounds(750, 290, 15, 15);
		fuelRegulatorOutPort1Shape.setRequestResetCaption(true);			
		
		// Create outgoing port 2 to FuelRegulator
		ISysMLFlowPort fuelRegulatorOutPort2Model = attrFuelPump.createSysMLFlowPort();
		fuelRegulatorOutPort2Model.setDirection(ISysMLFlowPort.DIRECTION_OUT);
		
		// Create diagram element for outgoing port 2 on FuelRegulator
		ISysMLFlowPortUIModel fuelRegulatorOutPort2Shape = (ISysMLFlowPortUIModel) diagramManager.createDiagramElement(diagram, fuelRegulatorOutPort2Model);
		partFuelRegulator.addChild(fuelRegulatorOutPort2Shape);
		fuelRegulatorOutPort2Shape.setBounds(860, 315, 15, 15);
		fuelRegulatorOutPort2Shape.setRequestResetCaption(true);			

		// Create incoming port to FuelRegulator
		ISysMLFlowPort fuelRegulatorInPortModel = attrFuelPump.createSysMLFlowPort();
		fuelRegulatorInPortModel.setDirection(ISysMLFlowPort.DIRECTION_IN);
		
		// Create diagram element for incoming port on FuelInjector1
		ISysMLFlowPortUIModel fuelRegulatorInPortShape = (ISysMLFlowPortUIModel) diagramManager.createDiagramElement(diagram, fuelRegulatorInPortModel);
		partFuelRegulator.addChild(fuelRegulatorInPortShape);
		fuelRegulatorInPortShape.setBounds(790, 315, 15, 15);
		fuelRegulatorInPortShape.setRequestResetCaption(true);			

		// Create part property FuelRail under InternalCombustionEngine
		IAttribute attrFuelRail = attrFuelTankAssy.createAttribute();
		attrFuelRail.setName("fra");
		attrFuelRail.setType(blkFuelRail);
		
		// Create diagram element for FuelRail
		ISysMLPropertyUIModel partFuelRail = (ISysMLPropertyUIModel) diagramManager.createDiagramElement(diagram, IShapeTypeConstants.SHAPE_TYPE_SYS_ML_PROPERTY);
		partFuelRail.setModelElement(attrFuelRail);
		partFuelRail.setBounds(580, 280, 110, 40);
		partInternalCombustionEngine.addChild(partFuelRail);
		partFuelRail.setRequestResetCaption(true);		
		
		// Create outgoing port1 to FuelRail
		ISysMLFlowPort fuelRailOutPort1Model = attrFuelPump.createSysMLFlowPort();
		fuelRailOutPort1Model.setDirection(ISysMLFlowPort.DIRECTION_OUT);
		
		// Create diagram element for incoming port on FuelInjector1
		ISysMLFlowPortUIModel fuelRailOutPort1Shape = (ISysMLFlowPortUIModel) diagramManager.createDiagramElement(diagram, fuelRailOutPort1Model);
		partFuelRail.addChild(fuelRailOutPort1Shape);
		fuelRailOutPort1Shape.setBounds(610, 270, 15, 15);
		fuelRailOutPort1Shape.setRequestResetCaption(true);			
		
		// Create outgoing port2 to FuelRail
		ISysMLFlowPort fuelRailOutPort2Model = attrFuelPump.createSysMLFlowPort();
		fuelRailOutPort2Model.setDirection(ISysMLFlowPort.DIRECTION_OUT);
		
		// Create diagram element for incoming port on FuelInjector1
		ISysMLFlowPortUIModel fuelRailOutPort2Shape = (ISysMLFlowPortUIModel) diagramManager.createDiagramElement(diagram, fuelRailOutPort2Model);
		partFuelRail.addChild(fuelRailOutPort2Shape);
		fuelRailOutPort2Shape.setBounds(660, 270, 15, 15);
		fuelRailOutPort2Shape.setRequestResetCaption(true);			
		
		// Create incoming port to FuelRail
		ISysMLFlowPort fuelRailInPortModel = attrFuelPump.createSysMLFlowPort();
		fuelRailInPortModel.setDirection(ISysMLFlowPort.DIRECTION_IN);
		
		// Create diagram element for incoming port on FuelInjector1
		ISysMLFlowPortUIModel fuelRailInPortShape = (ISysMLFlowPortUIModel) diagramManager.createDiagramElement(diagram, fuelRailInPortModel);
		partFuelRail.addChild(fuelRailInPortShape);
		fuelRailInPortShape.setBounds(680, 290, 15, 15);
		fuelRailInPortShape.setRequestResetCaption(true);			

		// Create connector between FuelRail and FuelInjector1
		IConnector connectorFuelRailFuelInjector1 = IModelElementFactory.instance().createConnector();
		connectorFuelRailFuelInjector1.setFrom(fuelRailOutPort1Model);
		connectorFuelRailFuelInjector1.setTo(fuelInjector1InPortModel);		
		diagramManager.createConnector(diagram, connectorFuelRailFuelInjector1, 
									fuelRailOutPort1Shape, fuelInjector1InPortShape, 
									new Point[] {new Point(618,270), new Point(618,157), new Point(755,157)} );

		// Create connector between FuelRain and FuelInjector2
		IConnector connectorFuelRailFuelInjector2 = IModelElementFactory.instance().createConnector();
		connectorFuelRailFuelInjector2.setFrom(fuelRailOutPort2Model);
		connectorFuelRailFuelInjector2.setTo(fuelInjector2InPortModel);		
		diagramManager.createConnector(diagram, connectorFuelRailFuelInjector2, 
									fuelRailOutPort2Shape, fuelInjector2InPortShape, 
									new Point[] {new Point(668,270), new Point(668,227), new Point(755,227)} );
		
		// Create connector between FuelRegulator and FuelRail
		IConnector connectorFuelRegulatorFuelRail = IModelElementFactory.instance().createConnector();
		connectorFuelRegulatorFuelRail.setFrom(fuelRegulatorOutPort1Model);
		connectorFuelRegulatorFuelRail.setTo(fuelRailInPortModel);		
		diagramManager.createConnector(diagram, connectorFuelRegulatorFuelRail, 
									fuelRegulatorOutPort1Shape, fuelRailInPortShape, 
									new Point[] {new Point(750,297), new Point(695,297)} );

		// Create connector between Engine and in port of FuelRegulator
		IConnector connectorEnineFuelRegularorInPort = IModelElementFactory.instance().createConnector();
		connectorEnineFuelRegularorInPort.setFrom(engineInOutPortModel);
		connectorEnineFuelRegularorInPort.setTo(fuelRegulatorInPortModel);		
		diagramManager.createConnector(diagram, connectorEnineFuelRegularorInPort, 
									engineInOutPortShape, fuelRegulatorInPortShape, 
									new Point[] {new Point(837,385), new Point(837,355), new Point(797,355), new Point(797,330)} );

		// Create connector between Engine and in port of FuelRegulator
		IConnector connectorEnineFuelRegularorOutPort = IModelElementFactory.instance().createConnector();
		connectorEnineFuelRegularorOutPort.setFrom(engineInOutPortModel);
		connectorEnineFuelRegularorOutPort.setTo(fuelRegulatorOutPort2Model);		
		diagramManager.createConnector(diagram, connectorEnineFuelRegularorOutPort, 
									engineInOutPortShape, fuelRegulatorOutPort2Shape, 
									new Point[] {new Point(837,385), new Point(837,355), new Point(867,355), new Point(867,330)} );
		
		// Create connector between port P1 of FuelTankAssy to engine
		IConnector connectorFuelTankAssyPort1Engine = IModelElementFactory.instance().createConnector();
		connectorFuelTankAssyPort1Engine.setName("fuelSupplyLine");
		connectorFuelTankAssyPort1Engine.setFrom(fuelTankAssyOutPortModel1);
		connectorFuelTankAssyPort1Engine.setTo(engineInOutPortModel);		
		IConnectorUIModel connectorFuelTankAssyPort1EngineShape = (IConnectorUIModel) diagramManager.createConnector(diagram, connectorFuelTankAssyPort1Engine, 
									fuelTankAssyOutPortShape1, engineInOutPortShape, 
									new Point[] {new Point(460,472), new Point(835,472), new Point(835,400)} );
		connectorFuelTankAssyPort1EngineShape.setRequestResetCaption(true);

		// Create connector between port P2 of FuelTankAssy to engine
		IConnector connectorFuelTankAssyPort2Engine = IModelElementFactory.instance().createConnector();
		connectorFuelTankAssyPort2Engine.setName("fuelReturnLink");
		connectorFuelTankAssyPort2Engine.setFrom(fuelTankAssyOutPortModel2);
		connectorFuelTankAssyPort2Engine.setTo(engineInOutPortModel);		
		IConnectorUIModel connectorFuelTankAssyPort2EngineShape = (IConnectorUIModel) diagramManager.createConnector(diagram, connectorFuelTankAssyPort2Engine, 
									fuelTankAssyOutPortShape2, engineInOutPortShape, 
									new Point[] {new Point(460,512), new Point(839,512), new Point(839,400)} );
		connectorFuelTankAssyPort2EngineShape.setRequestResetCaption(true);
		
		// Create attribute on FuelTankAssy for Item Flow
		IAttribute attrFuelSupply = blkFuelTankAssy.createAttribute();
		attrFuelSupply.setName("fuelSupply");
		attrFuelSupply.setType(blkFuel);
		
		// Create Item Flow from FuelTankAssy Port 1 to Inout port of Engine		
		ISysMLItemFlow itemFlow = connectorFuelTankAssyPort1Engine.createSysMLItemFlow();
		itemFlow.setItemProperty(attrFuelSupply);
		ISysMLItemFlowUIModel itemFlowShape = (ISysMLItemFlowUIModel) diagramManager.createDiagramElement(diagram, itemFlow);
		connectorFuelTankAssyPort1EngineShape.addChild(itemFlowShape);
		itemFlowShape.setBounds(510, 447, 50, 50);
		itemFlowShape.setRequestResetCaption(true);
		
		// Create Reverse Item Flow from FuelTankAssy Port 2 to Inout port of Engine
		ISysMLItemFlow reverseItemFlow = connectorFuelTankAssyPort1Engine.createSysMLItemFlow();
		reverseItemFlow.setItemProperty(attrFuelSupply);
		reverseItemFlow.setReversed(true);
		ISysMLItemFlowUIModel reverseItemFlowShape = (ISysMLItemFlowUIModel) diagramManager.createDiagramElement(diagram, reverseItemFlow);
		connectorFuelTankAssyPort1EngineShape.addChild(reverseItemFlowShape);
		reverseItemFlowShape.setBounds(560, 488, 50, 50);
		reverseItemFlowShape.setRequestResetCaption(true);
		
		diagramManager.openDiagram(diagram);
		
	}

	@Override
	public void update(VPAction arg0) {
		// TODO Auto-generated method stub
		
	}

}
