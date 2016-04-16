package j4jstat;

import java.util.List;

public class Display {
	public static void displayDataset(Dataset dataset) {
		
//		for (List<Integer> Ntuple : dataset.getCells().keySet()) {
//			System.out.println(Ntuple);
//			System.out.println(dataset.getCells().get(Ntuple).getValue());
//		}
//		
		
//		
		System.out.println("***ROOT LEVEL");
		System.out.println("     class: " + dataset.getClazz());
		System.out.println("     version: " + dataset.getVersion());
		System.out.println("     value: " + dataset.getValue());
		System.out.println("     status: " + dataset.getStatus());
		System.out.println("     dimensions list: " + dataset.getDimension());
		System.out.println("     label: " + dataset.getLabel());
		System.out.println("     updated: " + dataset.getUpdated());
		System.out.println("     source: " + dataset.getSource());
		System.out.println("     extension: " + dataset.getExtension());
		System.out.println("     href: " + dataset.getHref());

		System.out.println("     id: " + dataset.getId());
		System.out.println("     size: " + dataset.getSize());

		System.out.println();
		System.out.println("***ROLE");
		if (null != dataset.getRole()) {
			for (String roleID : dataset.getRole().keySet()) {
				System.out.println("     role: " + roleID);
				System.out.println("     dimensions with this role: " + dataset.getRole().get(roleID));
			}
		}
		System.out.println("***END ROLE");
		System.out.println();

		System.out.println();
		System.out.println("***EXTENSION");
		if (null != dataset.getExtension()) {
			for (String extentionID : dataset.getExtension().keySet()) {
				System.out.println("     extensionID: " + extentionID);
				System.out.println("     extension object: " + dataset.getExtension().get(extentionID));
			}
		}
		System.out.println("***END EXTENSION");
		System.out.println();

		System.out.println("***LINKS");
		if (null != dataset.getLink()) {
			for (String relationID : dataset.getLink().keySet()) {
				System.out.println("     relationID: " + relationID);
				for (Relation relation : dataset.getLink().get(relationID)) {
					System.out.println("     relation clazz: " + relation.getClazz());
					System.out.println("     relation href: " + relation.getHref());
					System.out.println("     relation type: " + relation.getType());
				}
			}
		}
		System.out.println("***END LINKS");
		System.out.println();

		System.out.println();
		System.out.println("***NOTE");
		if (null != dataset.getNote()) {
			for (String note : dataset.getNote()) {
				System.out.println("     note: " + note);
			}
		}
		System.out.println("***END NOTE");
		System.out.println();

		System.out.println();
		System.out.println("***ERROR");
		if (null != dataset.getError()) {
			for (String errorID : dataset.getError().keySet()) {
				System.out.println("     errorID: " + errorID);
				System.out.println("     error object: " + dataset.getError().get(errorID));
			}
		}
		System.out.println("***END ERROR");
		System.out.println();

		System.out.println();
		System.out.println("*********************DIMENSIONS LEVEL");

		for (String dimensionID : dataset.getDimension().keySet()) {
			System.out.println(" *******************id: " + dimensionID);

			Dimension dimension = dataset.getDimension().get(dimensionID);
			displayDimension(dimension);
		}

		System.out.println("*********************END DIMENSIONS LEVEL");
		System.out.println();

	}

	public static void displayDimension(Dimension dimension) {

		System.out.println("     clazz: " + dimension.getClazz());
		System.out.println("     label: " + dimension.getLabel());
		System.out.println("     href: " + dimension.getHref());

		System.out.println();
		System.out.println("***NOTE");
		if (null != dimension.getNote()) {
			for (String note : dimension.getNote()) {
				System.out.println("     note: " + note);
			}
		}
		System.out.println("***END NOTE");
		System.out.println();

		System.out.println();
		System.out.println("***EXTENSION");
		if (null != dimension.getExtension()) {
			for (String extentionID : dimension.getExtension().keySet()) {
				System.out.println("     extensionID: " + extentionID);
				System.out.println("     extension object: " + dimension.getExtension().get(extentionID));
			}
		}
		System.out.println("***END EXTENSION");
		System.out.println();

		System.out.println("***LINKS");
		if (null != dimension.getLink()) {
			for (String relationID : dimension.getLink().keySet()) {
				System.out.println("     relationID: " + relationID);
				for (Relation relation : dimension.getLink().get(relationID)) {
					System.out.println("     relation clazz: " + relation.getClazz());

					System.out.println();
					System.out.println("***EXTENSION");
					if (null != relation.getExtension()) {
						for (String extentionID : relation.getExtension().keySet()) {
							System.out.println("     extensionID: " + extentionID);
							System.out.println("     extension object: " + relation.getExtension().get(extentionID));
						}
					}
					System.out.println("***END EXTENSION");
					System.out.println();

					System.out.println("     relation href: " + relation.getHref());
					System.out.println("     relation type: " + relation.getType());
				}
			}
		}
		System.out.println("***END LINKS");
		System.out.println();

		System.out.println("***CATEGORY");
		System.out.println("     category label: " + dimension.getCategory().getLabel());
		System.out.println("     category index: " + dimension.getCategory().getIndex());

		System.out.println();
		System.out.println("***NOTE");
		if (null != dimension.getCategory().getNote()) {
			for (String noteId : dimension.getCategory().getNote().keySet()) {
				System.out.println("     noteID: " + noteId);
				for (String note : dimension.getCategory().getNote().get(noteId)) {
					System.out.println(note);
				}
			}
		}
		System.out.println("***END NOTE");
		System.out.println();

		System.out.println("***UNIT");
		if (null != dimension.getCategory().getUnit()) {
			for (String unitID : dimension.getCategory().getUnit().keySet()) {
				System.out.println("     unitID: " + unitID);
				for (String unit : dimension.getCategory().getUnit().get(unitID).keySet()) {
					System.out.print("     " + unit);
					System.out.println(": " + dimension.getCategory().getUnit().get(unitID).get(unit));
				}
			}
		}
		System.out.println("***END UNIT");
		System.out.println();

		System.out.println("***COORDINATES");
		if (null != dimension.getCategory().getCoordinates()) {
			for (String coorID : dimension.getCategory().getCoordinates().keySet()) {
				System.out.println("     coorID: " + coorID);
				System.out.println("     longitude: " + dimension.getCategory().getCoordinates().get(coorID).get(0));
				System.out.println("     latitude: " + dimension.getCategory().getCoordinates().get(coorID).get(1));
			}
		}
		System.out.println("***END COORDINATES");
		System.out.println();

		System.out.println("***CHILD");
		if (null != dimension.getCategory().getChild()) {
			for (String childID : dimension.getCategory().getChild().keySet()) {
				System.out.println("     childID: " + childID);
				System.out.println("     child list: " + dimension.getCategory().getChild().get(childID));
			}
		}
		System.out.println("***END CHILD");
		System.out.println();

		System.out.println("***END CATEGORY");
		System.out.println();

	}

	public static void displayCollection(Collection collection) {
		System.out.println("***ROOT LEVEL");
		System.out.println("     version: " + collection.getVersion());
		System.out.println("     class: " + collection.getClazz());
		System.out.println("     label: " + collection.getLabel());
		System.out.println("     updated: " + collection.getUpdated());
		System.out.println("     href: " + collection.getHref());
		
		for (Relation relation : collection.getLink().get("item")) {
			displayRelation(relation);
		}
	}

	public static void displayRelation(Relation relation) {
		System.out.println("***RELATION");
		System.out.println("     class: " + relation.getClazz());
//		System.out.println("     label: " + relation.getLabel());
		System.out.println("     href: " + relation.getHref());
		
	}

	
	
}
