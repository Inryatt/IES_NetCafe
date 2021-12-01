package controller;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StaticData {
    public static List<User> users = new ArrayList<>(Arrays.asList(
            new User(0, "adam_anderson@ies.com", "Adam Anderson", LocalDate.of(1997, 4, 23)),
            new User(1, "bob_baker@ies.com", "Bob Baker", LocalDate.of(1978, 11, 27)),
            new User(2, "carol_campbell@ies.com", "Carol Campbell", LocalDate.of(2001, 7, 2)),
            new User(3, "david_dawson@ies.com", "David Dawson", LocalDate.of(2000, 1, 14)),
            new User(4, "edward_evans@ies.com", "Edward Evans", LocalDate.of(1984, 2, 19)),
            new User(4, "francesca_freeman@ies.com", "Francesca Freeman", LocalDate.of(2002, 10, 3))
    ));

    public static List<Machine> machines = new ArrayList<>(Arrays.asList(
            new Machine(
                    0,
                    "Intel Core i5-10400 2.9 GHz 6-Core",
                    "MSI GeForce RTX 2070 SUPER 8 GB",
                    "16 GB",
                    "Samsung 970 Evo Plus 500 GB SSD",
                    "Windows 10"
            ),
            new Machine(
                    1,
                    "AMD Ryzen 5 3600 3.6 GHz 6-Core",
                    "Gigabyte Radeon RX 5600 XT 6 GB WINDFORCE OC",
                    "16 GB",
                    "ADATA XPG SX8200 Pro 500 SSD",
                    "Windows 11"
            ),
            new Machine(
                    2,
                    "AMD Ryzen 3 2200G",
                    "Radeon Vega 8",
                    "8 GB",
                    "Klevv CRAS C710 512 GB M.2-2280 NVME Solid State Drive",
                    "Windows 10"
            ),
            new Machine(
                    3,
                    "Intel Core i3-4330 3.5 GHz 2-Core",
                    "Intel HD Graphics 4600",
                    "8 GB",
                    "Intel 545s 256 GB",
                    "Ubuntu 20.04"
            ),
            new Machine(
                    4,
                    "AMD Ryzen 9 5950X 3.4 GHz 16-Core",
                    "NVIDIA GeForce RTX 3090 24 GB",
                    "32 GB",
                    "Samsung 980 Pro 1 TB M.2-2280 NVME SSD",
                    "Windows 11"
            )
    ));

    public static List<Software> softwares = new ArrayList<>(Arrays.asList(
            new Software(0, "Google Chrome", SoftwareCategory.WEB),
            new Software(1, "Firefox", SoftwareCategory.WEB),
            new Software(2, "Microsoft Edge", SoftwareCategory.WEB),
            new Software(3, "Discord", SoftwareCategory.COMMUNICATION),
            new Software(4, "Telegram", SoftwareCategory.COMMUNICATION),
            new Software(5, "Dead by Daylight", SoftwareCategory.GAMING),
            new Software(6, "Minecraft", SoftwareCategory.GAMING),
            new Software(7, "The Witcher 3", SoftwareCategory.GAMING),
            new Software(8, "Netflix", SoftwareCategory.ENTERTAINMENT),
            new Software(9, "Films & TV", SoftwareCategory.ENTERTAINMENT),
            new Software(10, "Microsoft Word", SoftwareCategory.PRODUCTIVITY),
            new Software(11, "Microsoft Excel", SoftwareCategory.PRODUCTIVITY)
    ));

    public static List<MachineUsage> currentMachineUsages = new ArrayList<>(Arrays.asList(
            new MachineUsage(
                    machines.get(0),
                    users.get(0),
                    LocalDate.now(),
                    0.7,
                    0.9,
                    0.3,
                    0.6,
                    70,
                    85,
                    25,
                    500,
                    2700,
                    Arrays.asList(
                            softwares.get(0),
                            softwares.get(3),
                            softwares.get(5)
                    )
            ),
            new MachineUsage(
                    machines.get(2),
                    users.get(1),
                    LocalDate.now(),
                    0.4,
                    0.1,
                    0.2,
                    0.4,
                    55,
                    30,
                    15,
                    250,
                    4680,
                    Arrays.asList(
                            softwares.get(1),
                            softwares.get(4),
                            softwares.get(10)
                    )
            )
    ));
}
