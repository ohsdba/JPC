package org.jpc.emulator.execution.opcodes.vm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class inc_o16_eSP extends Executable
{

    public inc_o16_eSP(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
    }

    public Branch execute(Processor cpu)
    {
        cpu.cf = Processor.getCarryFlag(cpu.flagStatus, cpu.cf, cpu.flagOp1, cpu.flagOp2, cpu.flagResult, cpu.flagIns);
        cpu.flagOp1 = (short)cpu.r_esp.get16();
        cpu.flagOp2 = 1;
        cpu.flagResult = (short)(cpu.flagOp1 + 1);
        cpu.r_esp.set16((short)cpu.flagResult);
        cpu.flagIns = UCodes.ADD16;
        cpu.flagStatus = NCF;
        return Branch.None;
    }

    public boolean isBranch()
    {
        return false;
    }

    public String toString()
    {
        return this.getClass().getName();
    }
}